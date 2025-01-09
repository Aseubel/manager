package com.guyuan.manager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guyuan.manager.Entity.TeamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 杨之耀
 * @description 团队数据库操作接口
 * @date 2024/12/12
 */
@Mapper
public interface TeamMapper extends BaseMapper<TeamEntity> {

    /**
     * 根据团队名称获取团队id
     * @param teamName
     * @return
     */
    String getTeamIdByTeamName(@Param("teamName") String teamName);
}
