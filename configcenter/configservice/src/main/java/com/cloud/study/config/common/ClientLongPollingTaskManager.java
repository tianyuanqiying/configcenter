package com.cloud.study.config.common;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.study.dto.LongPullDTO;
import org.omg.CORBA.TIMEOUT;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * 长轮训任务管理器
 * @author user
 */
public class ClientLongPollingTaskManager {
    /**
     * 任务队列
     */
    Queue<ClientLongPollingTask> tasks;

    ScheduledExecutorService longPullServiceExecutor;

    Map<String, Long> retainIps = new HashMap<>();

    {
        tasks = new  ConcurrentLinkedQueue<>();
        longPullServiceExecutor = Executors.newScheduledThreadPool(1, new NameThreadFactory("LongPullService"));
    }

    public void addClientLongPoolingTask(AsyncContext asyncContext, Map<String, List<String>> clientMd5Map, long timeout, String remoteIp, long currentTime, String clientAppName) {
        longPullServiceExecutor.execute(new ClientLongPollingTask(asyncContext, clientMd5Map, timeout, remoteIp, currentTime, clientAppName));
    }

    public class ClientLongPollingTask implements Runnable {
        private AsyncContext asyncContext;
        private Map<String, List<String>> clientMd5Map;
        private long timeout;
        private String remoteIp;
        private long currentTime;
        private String appName;
        Future<?> asyncTimeoutFuture;
        public ClientLongPollingTask(AsyncContext asyncContext, Map<String, List<String>> clientMd5Map, long timeout, String remoteIp, long currentTime, String clientAppName) {
            this.asyncContext = asyncContext;
            this.clientMd5Map = clientMd5Map;
            this.timeout = timeout;
            this.remoteIp = remoteIp;
            this.currentTime = currentTime;
            this.appName = clientAppName;
        }

        @Override
        public void run() {
            asyncTimeoutFuture = longPullServiceExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    try{
                        retainIps.put(remoteIp, System.currentTimeMillis());

                        //从队列中取出长轮训任务；
                        boolean removeFlag = tasks.remove(ClientLongPollingTask.this);

                        if (!removeFlag) {
                            System.out.println("client subsciber's relations delete fail.");
                            return;
                        }

                        //拿到变化的keys
                        List<LongPullDTO> changeKeys = compareMd5(clientMd5Map);

                        //结束，写入响应；
                        sendResp(changeKeys);
                    }catch (Exception e) {
                        //线程任务一直执行，避免线程挂了。避免重复创建线程，消耗系统资源
                        System.out.println("long polling error");
                        System.out.println(e.getMessage());
                        System.out.println(e.getCause());
                    }

                }
            }, timeout, TimeUnit.MILLISECONDS);

            tasks.add(this);
        }

        public List<LongPullDTO> compareMd5(Map<String, List<String>> clientMd5Map) {
            return new ArrayList<>();
        }

        void sendResp(List<LongPullDTO> changekeys) throws IOException {
            if (asyncTimeoutFuture != null) {
                asyncTimeoutFuture.cancel(false);
            }

            generateResp(changekeys);
        }

        private void generateResp(List<LongPullDTO> changekeys) {
            if (CollectionUtil.isEmpty(changekeys)) {
                //没有key发生改动，则结束；
                asyncContext.complete();
                return;
            }

            HttpServletResponse response = (HttpServletResponse) asyncContext.getResponse();
            String changeKeysJsonResult = JSONUtil.toJsonStr(changekeys);

            //写入响应头以及响应数据；
            //禁用缓存
            response.setHeader("Cache-Control", "no-cache,no-store");
            //写入状态码
            response.setStatus(HttpServletResponse.SC_OK);
            //写入数据
            try {
                response.getWriter().println(changeKeysJsonResult);
                asyncContext.complete();
            } catch (IOException e) {
                e.printStackTrace();
                asyncContext.complete();
            }
        }
    }
}
