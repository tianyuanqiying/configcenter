package com.cloud.study.config.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.cloud.study.config.pojo.CacheData;
import com.cloud.study.constants.Constants;
import com.cloud.study.dto.LongPullDTO;

import java.util.*;
import java.util.concurrent.*;

import static com.cloud.study.constants.Constants.TEMP_APP_NAME;

/**
 * 实现发起长轮训请求
 * @author user
 */
public class ClientWorker
{
    private String CONFIG_SERVER_URI = "http://127.0.0.1:8848";

    /**
     * key = dataId + groupId
     */
    ConcurrentHashMap<String, CacheData> cacheMap = new ConcurrentHashMap<String, CacheData>();

    /**
     * 一级线程池
     */
    ScheduledExecutorService executor;

    /**
     * 执行长轮训线程池
     */
    ScheduledExecutorService executorLongPullingService;
    public ClientWorker() {
        this.executor = Executors.newScheduledThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("com.cloud.study.ClientWorker");
                t.setDaemon(true);
                return t;
            }
        });

        this.executorLongPullingService = Executors
                .newScheduledThreadPool(Runtime.getRuntime().availableProcessors(), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("com.alibaba.nacos.client.Worker.longPolling.ClientWorker" );
                        t.setDaemon(true);
                        return t;
                    }
                });



        executor.scheduleWithFixedDelay(() -> {
            try{
                checkUpdateInfo();
            }catch (Exception e) {
                System.out.println("一级检查自旋任务失败、发送异常：" + e);
            }
        }, 1L, 10L, TimeUnit.MILLISECONDS);
    }

    private void checkUpdateInfo() {
        executorLongPullingService.execute(new LongPollingRunnable(0));
    }

    /**
     * 长轮训任务
     */
    class LongPollingRunnable implements Runnable{
        int page = 0;
        public LongPollingRunnable(int page) {
            this.page = page;
        }

        @Override
        public void run() {
            //拿到配置
            Collection<CacheData> caches = cacheMap.values();

            if (caches.size() == 0) {
                return;
            }

            ArrayList<LongPullDTO> longPullDTOList = new ArrayList<>();
            for (CacheData data : caches) {
                LongPullDTO longPullDTO = new LongPullDTO(data.getDataId(), data.getGroupId(), data.getMd5());
                longPullDTOList.add(longPullDTO);
            }

            Map<String, Object> httpBody = new HashMap<>();
            //设置检查的配置项
            httpBody.put(Constants.PROBE_MODIFY_REQUEST, longPullDTOList);
            //设置APP名称
            httpBody.put(Constants.CLIENT_APP_NAME, TEMP_APP_NAME);
            //设置长轮训默认超时时间
            httpBody.put(Constants.LONG_POLLING_TIMEOUT, Constants.LONG_POLLING_TIMEOUT_DEFAULT_VAL);

            String listenerUrl = CONFIG_SERVER_URI + "/listener";

            //发起Post请求，获取变更的配置项；
            String changeConfigStr = HttpUtil.post(listenerUrl, httpBody, Constants.LONG_POLLING_TIMEOUT_DEFAULT_VAL);

            //解析结果，获取变更的配置；dataId, groupId;
            List<LongPullDTO> changeConfigs = JSONUtil.toList(JSONUtil.parseArray(changeConfigStr), LongPullDTO.class);

            for (LongPullDTO changeConfig : changeConfigs) {
                String queryConfigUrl = CONFIG_SERVER_URI + Constants.QUERY_CONFIG;
                Map<String, Object> queryParam = new HashMap<>();
                queryParam.put("dataId", changeConfig.getDataId());
                queryParam.put("groupId", changeConfig.getGroupId());
                String configJsonStr = HttpUtil.get(queryConfigUrl, queryParam);
                CacheData cacheData = JSONUtil.toBean(configJsonStr, CacheData.class);
                //更新缓存；
                cacheMap.put(cacheData.getDataId() + cacheData.getGroupId(), cacheData);
            }
        }
    }
}
