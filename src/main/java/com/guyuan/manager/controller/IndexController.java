package com.guyuan.manager.controller;

import com.guyuan.manager.util.HostHolder;
import com.guyuan.manager.util.HttpClientUtils;
import com.guyuan.manager.util.TransferScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yangzhiyao
 * @description 首页控制器
 * @date 2024/12/4
 */
@Slf4j
@Controller
public class IndexController implements Initializable {

    @FXML private Label pageIndex;
    @FXML private Pane contentBox;

    @Autowired
    private TransferScene transferScene;

    private static String getPageIndex() {
        return "页码：" + TransferScene.getSceneIndex() + "/" + TransferScene.getScenes().size();
    }

    private static Boolean isNeededToRecord(String fxmlFileName) {
        return TransferScene.getSceneIndex()==0 || !TransferScene.getNowScene().equals(fxmlFileName);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void searchUser(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("searchUser");
            transferScene.loadScene(contentBox, "searchUser", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问查找用户页面失败！",e);
        }
    }
    @FXML
    public void addUser(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("addUser");
            transferScene.loadScene(contentBox, "addUser", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问添加用户页面失败！",e);
        }
    }
    @FXML
    public void modifyUser(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("modifyUser");
            transferScene.loadScene(contentBox, "modifyUser", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问修改用户信息页面失败！",e);
        }
    }

    @FXML
    public void listTeam(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("listTeam");
            transferScene.loadScene(contentBox, "listTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问团队列表页面失败！",e);
        }
    }
    @FXML
    public void searchTeam(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("searchTeam");
            transferScene.loadScene(contentBox, "searchTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问查找团队页面失败！",e);
        }
    }
    @FXML
    public void addTeam(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("addTeam");
            transferScene.loadScene(contentBox, "addTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问添加团队页面失败！",e);
        }
    }
    @FXML
    public void modifyTeam(ActionEvent actionEvent) {
        try {
            if(!HttpClientUtils.doGet("auth")) {
                return;
            }
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("modifyTeam");
            transferScene.loadScene(contentBox, "modifyTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            log.error("访问修改团队页面失败！",e);
        }
    }

    @FXML
    public void lastPage(ActionEvent actionEvent) {
        transferScene.lastScene(contentBox);
        pageIndex.setText(getPageIndex());
    }
    @FXML
    public void nextPage(ActionEvent actionEvent) {
        transferScene.nextScene(contentBox);
        pageIndex.setText(getPageIndex());
    }
    @FXML
    public void clearAll(ActionEvent actionEvent) {
        TransferScene.clear();
        TransferScene.init();
        contentBox.getChildren().clear();
        pageIndex.setText(getPageIndex());
    }
    @FXML
    public void logout(ActionEvent actionEvent) throws Exception {
        HostHolder.clear();
        TransferScene.clear();
        transferScene.transfer((Stage) pageIndex.getScene().getWindow(), "login");
    }

}
