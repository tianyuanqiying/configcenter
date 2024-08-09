package com.cloud.study.config.controller;

import com.cloud.study.config.service.LongPullService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author user
 */
@RestController
@RequestMapping("/listener")
public class ListenerController {
    @Autowired
    LongPullService longPullService;

    @PostMapping
    public void listener(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody HashMap<String, Object> body) {
        longPullService.addLongPullService(httpServletRequest, httpServletResponse, body);
    }
}
