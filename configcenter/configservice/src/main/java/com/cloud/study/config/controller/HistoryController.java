package com.cloud.study.config.controller;

import com.cloud.study.dto.ConfigDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author user
 */
@RestController
public class HistoryController {
    @GetMapping
    public Map<String, Object> get(ConfigDTO configDTO) {
        return new HashMap<String, Object>();
    }
}
