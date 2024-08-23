package com.cloud.study.dto;

/**
 * 长轮训实体类
 * @author user
 */
public class LongPullDTO {
    private String dataId;
    private String groupId;
    private String contentMd5;

    public LongPullDTO() {
    }

    public LongPullDTO(String dataId, String groupId, String contentMd5) {
        this.dataId = dataId;
        this.groupId = groupId;
        this.contentMd5 = contentMd5;
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

    public String getContentMd5() {
        return contentMd5;
    }

    public void setContentMd5(String contentMd5) {
        this.contentMd5 = contentMd5;
    }
}
