package com.cloud.study.config.service;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.study.config.Application;
import com.cloud.study.config.service.ConfigService;
import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.dto.ConfigUpdateDTO;


import com.cloud.study.vo.ConfigVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ConfigServiceTest {
    @Autowired
    ConfigService configService;

    @Test
    public void testSave() {
        ConfigUpdateDTO updateDTO = new ConfigUpdateDTO();
        updateDTO.setContent("123456");
        updateDTO.setCreateTime(new Date());
        updateDTO.setDataId("emm_base");
        updateDTO.setGroupId("default");
        updateDTO.setEnvironmentName("default");
        updateDTO.setMd5(SecureUtil.md5(updateDTO.getContent()));
        updateDTO.setUpdateTime(new Date());
        updateDTO.setTenant("");
        updateDTO.setEnvironmentName("");
        updateDTO.setId(UUID.randomUUID().toString());
        configService.saveOrUpdateConfig(updateDTO);
    }

    @Test
    public void testUpdate() {
        ConfigUpdateDTO updateDTO = new ConfigUpdateDTO();
        updateDTO.setContent("123456");
        updateDTO.setCreateTime(new Date());
        updateDTO.setGroupId("default");
        updateDTO.setEnvironmentName("default");
        updateDTO.setMd5(SecureUtil.md5(updateDTO.getContent()));
        updateDTO.setUpdateTime(new Date());
        updateDTO.setTenant("");
        updateDTO.setEnvironmentName("");
        updateDTO.setId("1");
        configService.saveOrUpdateConfig(updateDTO);
    }

    @Test
    public void testGet() {
        ConfigDTO updateDTO = new ConfigDTO();

        updateDTO.setDataId("emm_base");
        updateDTO.setGroupId("default");

        ConfigVO configVO = configService.get(updateDTO);
        System.out.println(JSONUtil.parse(configVO));
    }

}
