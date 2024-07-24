package com.cloud.study.entity;

import java.util.Date;

/**
 * 历史表配置
 * @author user
 */
public class ConfigHistoryEntity {
    private String id;
    private String dataId;
    private String groupId;
    private String content;
    private String md5;
    private Date createTime;
    private Date updateTIme;
    private Integer version;
    private String operatorName;
    private String operatorIp;
}
