package com.cloud.study.dto;

import java.util.Date;

/**
 * 配置类
 * @author zqh
 */
public class ConfigDTO {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
