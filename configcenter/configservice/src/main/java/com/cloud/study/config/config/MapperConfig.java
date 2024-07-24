package com.cloud.study.config.config;

import tk.mybatis.spring.annotation.MapperScan;

/**
 * mapper扫描配置
 * @author user
 */
@MapperScan("com.cloud.study.*.mapper")
public class MapperConfig {
}
