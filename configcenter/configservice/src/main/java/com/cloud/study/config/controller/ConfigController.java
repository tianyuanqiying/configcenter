package com.cloud.study.config.controller;

import com.cloud.study.dto.ConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置类接口
 * @author user
 */
@RestController("/config")
public class ConfigController {

    @GetMapping
    public Map<String, Object> get(ConfigDTO configDTO) {
        return new HashMap<String, Object>();
    }

    @PostMapping
    public Map<String, Object> saveOrUpdateConfig(ConfigDTO map) {
        return new HashMap<String, Object>();
    }

    @DeleteMapping
    public Map<String, Object> delete(String id) {
        return new HashMap<String, Object>();
    }
}
