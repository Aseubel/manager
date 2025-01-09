package com.guyuan.manager.controller;

import com.guyuan.manager.Entity.TeamEntity;
import com.guyuan.manager.Entity.UserEntity;
import com.guyuan.manager.exception.MyException;
import com.guyuan.manager.service.ITeamService;
import com.guyuan.manager.service.IUserService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.guyuan.manager.common.Constants.TEAM_PER_PAGE_MEMBER_HEIGHT;
import static com.guyuan.manager.common.Constants.TEAM_PER_PAGE_MEMBER_LIMIT;

/**
 * @author 杨之耀
 * @description 团队管理控制器
 * @date 2024/12/12
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class TeamController implements Initializable {

    private final ITeamService teamService;

    private final IUserService userService;

    @FXML private TextField searchTeamNameField;
    @FXML private Label teamNameField;
    @FXML private Label leaderField;
    @FXML private Label memberCountField;
    @FXML private Label createTimeField;
    @FXML private Label contactField;
    @FXML private TableView<UserEntity> membersTableView;
    @FXML private TableView<TeamEntity> teamTableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        switch (url.getFile().substring(url.getFile().lastIndexOf("/")+1)) {
            case "listTeam.fxml":
                List<TeamEntity> TeamEntityList = teamService.list();
                teamTableView.getItems().addAll(TeamEntityList);
                break;
            case "searchTeam.fxml":
                teamNameField.setText("");
                leaderField.setText("");
                memberCountField.setText("");
                createTimeField.setText("");
                contactField.setText("");
                membersTableView.setMaxHeight(TEAM_PER_PAGE_MEMBER_HEIGHT * TEAM_PER_PAGE_MEMBER_LIMIT);
            default:
        }
    }

    public void handleSearchTeam(ActionEvent actionEvent) {
        try {
            teamNameField.setText("");
            leaderField.setText("");
            memberCountField.setText("");
            createTimeField.setText("");
            contactField.setText("");

            TeamEntity team = teamService.getById(searchTeamNameField.getText());
            if (team != null) {
                teamNameField.setText(team.getTeamName());
                memberCountField.setText(String.valueOf(team.getMemberCount()));
                leaderField.setText(team.getLeader());
                createTimeField.setText(String.valueOf(team.getCreateTime()));
                contactField.setText(team.getContact());

                ObservableList<UserEntity> memberList = membersTableView.getItems();
                String lastUserId = memberList.isEmpty() ? null : memberList.get(memberList.size()-1).getUserId();
                memberList.clear();
                memberList.addAll(userService.
                        queryMemberList(team.getTeamId(), lastUserId, TEAM_PER_PAGE_MEMBER_LIMIT));
            }
        } catch (MyException e) {
            log.error("查询团队时出现异常, {}", e.getMessage());
        } catch (Exception e) {
            log.error("查询团队时出现未知异常",e);
        }
    }
}
