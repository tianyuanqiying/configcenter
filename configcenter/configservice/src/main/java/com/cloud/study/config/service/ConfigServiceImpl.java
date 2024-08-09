package com.cloud.study.config.service;

import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.vo.ConfigVO;
import org.springframework.stereotype.Service;

/**
 * @author user
 */
@Service
public class ConfigServiceImpl implements ConfigService{
    @Override
    public ConfigVO get(ConfigDTO configDTO) {
        return null;
    }

    @Override
    public Boolean saveOrUpdateConfig(ConfigDTO configDTO) {
        return null;
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }
}
