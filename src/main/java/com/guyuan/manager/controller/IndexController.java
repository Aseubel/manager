package com.guyuan.manager.controller;

import cn.hutool.core.util.ObjectUtil;
import com.guyuan.manager.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.guyuan.manager.ManagerApplication.applicationContext;

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
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("searchUser");
            transferScene.loadScene(contentBox, "searchUser", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void addUser(ActionEvent actionEvent) {
        try {
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("addUser");
            transferScene.loadScene(contentBox, "addUser", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void modifyUser(ActionEvent actionEvent) {
        try {
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("modifyUser");
            transferScene.loadScene(contentBox, "modifyUser", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void listTeam(ActionEvent actionEvent) {
        try {
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("listTeam");
            transferScene.loadScene(contentBox, "listTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void searchTeam(ActionEvent actionEvent) {
        try {
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("searchTeam");
            transferScene.loadScene(contentBox, "searchTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void addTeam(ActionEvent actionEvent) {
        try {
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("addTeam");
            transferScene.loadScene(contentBox, "addTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void modifyTeam(ActionEvent actionEvent) {
        try {
            // 当前页面不变，则为false不记录页面历史
            Boolean type = isNeededToRecord("modifyTeam");
            transferScene.loadScene(contentBox, "modifyTeam", type);
            pageIndex.setText(getPageIndex());
        } catch (Exception e) {
            throw new RuntimeException(e);
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