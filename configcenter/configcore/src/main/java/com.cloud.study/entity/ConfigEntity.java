package com.cloud.study.entity;

import java.util.Date;

/**
 * 配置类
 * @author zqh
 */
public class ConfigEntity {
    /**
     * 数据库主键，使用UUID
     */
    private String id;
    /**
     * 配置文件名
     */
    private String dataId;
    /**
     * 分组名
     */
    private String groupId;
    /**
     * 环境
     */
    private String environmentName;

    /**
     * 配置文件内容
     */
    private String content;
    /**
     * 文件摘要
     */
    private String md5;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
