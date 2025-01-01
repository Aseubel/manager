package com.guyuan.manager.service.impl.team;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guyuan.manager.Entity.TeamEntity;
import com.guyuan.manager.dao.TeamMapper;
import com.guyuan.manager.service.ITeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, TeamEntity> implements ITeamService {
}
