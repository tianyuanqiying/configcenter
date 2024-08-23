package com.cloud.study.config.mapper;

import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.entity.ConfigEntity;
import com.cloud.study.vo.ConfigVO;
import org.apache.ibatis.annotations.*;

/**
 * @author user
 */
@Mapper
public interface ConfigMapper {

    /**
     * 更新配置
     * @param configEntity
     * @return
     */
    @Insert("INSERT INTO config_info ( data_id, group_id, environment_name, content, md5, create_time, update_time ) " +
            "VALUE (#{configEntity.dataId}, #{configEntity.groupId}, #{configEntity.environmentName}, #{configEntity.content}, #{configEntity.md5}, #{configEntity.createTime}, #{configEntity.updateTime})")
    Boolean insert(@Param("configEntity") ConfigEntity configEntity);

    /**
     * 删除配置
     * @param id
     * @return
     */
    @Delete("delete from config_info where id = #{id}")
    Boolean deleteById(String id);

    @Select("select * from config_info where data_id = #{configDTO.dataId} AND group_id = #{configDTO.groupId}")
    ConfigEntity get(@Param("configDTO") ConfigDTO configDTO);

    @Update("update config_info set content = #{content} , md5 = #{md5}, update_time = #{updateTime} where id = #{id}")
    void update(ConfigEntity configEntity);
}
