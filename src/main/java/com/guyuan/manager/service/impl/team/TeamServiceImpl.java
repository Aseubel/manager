package com.guyuan.manager.service.impl.team;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guyuan.manager.Entity.TeamEntity;
import com.guyuan.manager.dao.PositionMapper;
import com.guyuan.manager.dao.TeamMapper;
import com.guyuan.manager.dao.po.PositionPO;
import com.guyuan.manager.service.ITeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, TeamEntity> implements ITeamService {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private PositionMapper positionMapper;

    /**
     * 查询某职位/分组下一级的所有职位/分组
     * @param positionName 需要查询子职位/分组的名称
     * @param teamName 团队名称
     * @return 下级职位/分组列表
     * @date 2024/11/7
     */
    @Override
    public List<String> querySubordinatePosition(String positionName, String teamName) {
        String teamId = teamMapper.getTeamIdByTeamName(teamName);
        String positionId = positionMapper.getPositionIdByPositionNameAndTeamId(positionName, teamId);
        // 从数据库中查询下级职位/分组
        List<PositionPO> positionPOList = positionMapper.
                listSubordinateByPositionId(positionId);
        List<String> positionNames = new ArrayList<>();
        for (PositionPO positionPO : positionPOList) {
            positionNames.add(positionPO.getPositionName());
        }

        return positionNames;
    }
}
