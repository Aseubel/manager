package com.guyuan.manager.Entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author 杨之耀
 * @description 团队实体类
 * @date 2024/12/3
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamEntity {
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
