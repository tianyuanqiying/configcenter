package com.cloud.study.config.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.cloud.study.config.common.ClientLongPollingTaskManager;
import com.cloud.study.config.common.NameThreadFactory;
import com.cloud.study.config.common.Constants;
import com.cloud.study.config.utils.RequestUtil;
import com.cloud.study.dto.LongPullDTO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LongPullService implements ILongPullService, InitializingBean{
    ClientLongPollingTaskManager clientLongPollingTaskManager;

    @Override
    public void addLongPullService(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> body) {
        if (body.containsKey(Constants.LONG_POLLING_TIMEOUT)) {
            body.put(Constants.LONG_POLLING_TIMEOUT, Constants.LONG_POLLING_TIMEOUT_DEFAULT_VAL);
        }

        Integer delayTime = Constants.FIXED_DELAY_TIME_DEFAULT_VAL;

        String clientAppName = body.get(Constants.CLIENT_APP_NAME).toString();

        //长轮训超时时间：默认29.5；
        long timeout = Math.max(10000, Long.parseLong(body.get(Constants.LONG_POLLING_TIMEOUT).toString()) - delayTime);

        //当前时间
        long currentTime = System.currentTimeMillis();

        //客户端配置信息
        Map<String, List<String>> clientMd5Map = getClientMd5Map(body);

        //远程IP
        String remoteIp = RequestUtil.getRemoteIp(request);

        //开启异步请求
        AsyncContext asyncContext = request.getAsyncContext();

        //添加长轮训任务
        clientLongPollingTaskManager.addClientLongPoolingTask(asyncContext, clientMd5Map, timeout, remoteIp, currentTime, clientAppName);
    }

    /**
     * 数据格式：
     * [
     *  {
     *     "dataId":xxx;
     *     "groupId":xxx;
     *     "md5":xxx;
     *  }
     *  ....
     * ]
     * @param body 请求体数据
     * @return 客户端配置
     */
    private Map<String, List<String>> getClientMd5Map(HashMap<String, Object> body) {
        String listeningConfigJsonStr = body.get(Constants.PROBE_MODIFY_REQUEST).toString();

        JSONArray listeningConfigs = JSONUtil.parseArray(listeningConfigJsonStr);

        List<LongPullDTO> longPullDTOList = JSONUtil.toList(listeningConfigs, LongPullDTO.class);

        HashMap<String, List<String>> clientMd5Map = new HashMap<>();

        for (LongPullDTO longPullDTO : longPullDTOList) {
            String clientKey = longPullDTO.getGroupId() + "-" + longPullDTO.getDataId();
            String contentMd5 = longPullDTO.getContentMd5();
            if (!clientMd5Map.containsKey(clientKey)) {
                clientMd5Map.put(clientKey, new ArrayList<>());
            }
            clientMd5Map.get(clientKey).add( contentMd5);
        }
        return clientMd5Map;
    }

    @Override
    public void afterPropertiesSet() {
        clientLongPollingTaskManager = new ClientLongPollingTaskManager();
    }
}
