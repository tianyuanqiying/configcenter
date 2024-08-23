package com.cloud.study.config.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.cloud.study.config.pojo.CacheData;
import com.cloud.study.constants.Constants;
import com.cloud.study.dto.ConfigDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientWorkerTest {

    @Test
    public void testQueryConfig() {
        ConfigDTO configDTO = new ConfigDTO();
        configDTO.setDataId("emm_base");
        configDTO.setGroupId("default");

        String queryConfigUrl = "http://localhost:8848" + Constants.QUERY_CONFIG;
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("dataId", configDTO.getDataId());
        queryParam.put("groupId", configDTO.getGroupId());
        String configJsonStr = HttpUtil.get(queryConfigUrl, queryParam);
        System.out.println(JSONUtil.parse(configJsonStr));
        CacheData cacheData = JSONUtil.toBean(configJsonStr, CacheData.class);
        System.out.println(cacheData);
    }

}
