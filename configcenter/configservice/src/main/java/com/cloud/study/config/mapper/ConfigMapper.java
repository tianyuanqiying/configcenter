package com.cloud.study.config.mapper;

import com.cloud.study.dto.ConfigDTO;
import com.cloud.study.vo.ConfigVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author user
 */
@Mapper
public interface ConfigMapper {

    /**
     * 查询配置详情
     * @param id
     * @return
     */
    @Select("select * from config_info where id = #{id}")
    ConfigVO get(String id);

    /**
     * 更新配置
     * @param configDTO
     * @return
     */
    @Insert("INSERT INTO (data_id, group_id, environment_name, content, md5, create_time, update_time) " +
            "VALUES (#{dataId}, #{groupId}, #{environmentName}, #{content}, #{md5}, #{createTime}, #{updateTime})")
    Boolean insert(ConfigDTO configDTO);

    /**
     * 删除配置
     * @param id
     * @return
     */
    @Delete("delete from config_info where id = #{id}")
    Boolean deleteById(String id);
}
