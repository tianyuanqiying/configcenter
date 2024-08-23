package com.cloud.study.dto;

import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 * 配置类
 * @author zqh
 */

@ApiModel(value = "配置更新类", description = "配置更新类")
public class ConfigUpdateDTO {
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

    /**
     * 租户
     */
    private String tenant;

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

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
