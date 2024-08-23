package com.cloud.study.config.controller;

import com.cloud.study.config.service.ConfigService;
import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.dto.ConfigUpdateDTO;
import com.cloud.study.vo.ConfigVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置类接口
 * @author user
 */

@Api(tags = "配置数据接口")
@RestController
@RequestMapping("/config")
public class ConfigController {
    @Autowired
    ConfigService configService;

    @GetMapping("/get")
    public ConfigVO get(ConfigDTO configDTO) {
        return configService.get(configDTO);

    }

    @PostMapping("/update")
    public Boolean saveOrUpdateConfig(ConfigUpdateDTO map) {
        return configService.saveOrUpdateConfig(map);
    }

    @PostMapping("delete")
    public Map<String, Object> delete(String id) {
        return new HashMap<String, Object>();
    }
}
