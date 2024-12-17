package com.guyuan.manager.dao.po;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TeamPO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 团队ID
     */
    private String teamId;

    /**
     * 团队名称
     */
    private String teamName;

    /**
     * 团队描述
     */
    private String teamDescription;

    /**
     * 团队成员数量
     */
    private String memberCount;

    /**
     * 团队创建时间
     */
    private LocalDateTime createTime;

    /**
     * 团队负责人
     */
    private String leader;
}
