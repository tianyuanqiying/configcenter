package com.cloud.study.config.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author user
 */
public interface ILongPullService {
    /**
     * 添加长轮训任务
     * @param request 请求
     * @param response 响应
     * @param body 请求体数据
     */
    void addLongPullService(HttpServletRequest request, HttpServletResponse response, HashMap<String, Object> body);
}
