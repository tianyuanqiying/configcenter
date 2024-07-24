package com.cloud.study.config.service;

import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.vo.ConfigVO;

public interface ConfigService {
    /**
     * 查询配置详情
     * @param configDTO
     * @return
     */
    ConfigVO get(ConfigDTO configDTO);

    /**
     * 更新配置
     * @param configDTO
     * @return
     */
    Boolean saveOrUpdateConfig(ConfigDTO configDTO);

    /**
     * 删除配置
     * @param id
     * @return
     */
    Boolean deleteById(String id);
}
