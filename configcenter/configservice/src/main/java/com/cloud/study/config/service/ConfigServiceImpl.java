package com.cloud.study.config.service;

import cn.hutool.core.util.StrUtil;
import com.cloud.study.config.mapper.ConfigMapper;
import com.cloud.study.config.utils.StringUtils;
import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.dto.ConfigUpdateDTO;
import com.cloud.study.entity.ConfigEntity;
import com.cloud.study.vo.ConfigVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author user
 */
@Service
public class ConfigServiceImpl implements ConfigService{
    @Autowired
    ConfigMapper configMapper;

    @Override
    public ConfigVO get(ConfigDTO configDTO) {
        ConfigEntity configEntity = configMapper.get(configDTO);
        ConfigVO configVO = new ConfigVO();
        BeanUtils.copyProperties(configEntity, configVO);
        return configVO;
    }

    @Override
    public Boolean saveOrUpdateConfig(ConfigUpdateDTO configDTO) {
        ConfigEntity configEntity = new ConfigEntity();
        BeanUtils.copyProperties(configDTO, configEntity);
        if (StrUtil.isBlank(configDTO.getId())) {
            configMapper.insert(configEntity);
        }else {
            configMapper.update(configEntity);
        }

        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        return null;
    }
}
