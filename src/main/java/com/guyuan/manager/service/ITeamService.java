package com.guyuan.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guyuan.manager.Entity.TeamEntity;

import java.util.List;

/**
 * @author yzy
 * @description 团队服务统一接口
 * @date 2024/12/19
 */
public interface ITeamService extends IService<TeamEntity> {

    List<String> querySubordinatePosition(String positionName, String teamName);
}
