package com.guyuan.manager.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
@TableName("team")
public class TeamEntity {
    /**
     * 团队ID
     */
    private String teamId;

    /**
     * 团队名称
     */
    @TableId
    private String teamName;

    /**
     * 团队成员数量
     */
    private String memberCount;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 团队创建时间
     */
    private LocalDateTime createTime;

    /**
     * 团队负责人
     */
    private String leader;

    public String getCreateTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return createTime != null ? createTime.format(formatter) : "";
    }
}

