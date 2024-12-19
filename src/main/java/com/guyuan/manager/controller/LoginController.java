package com.guyuan.manager.controller;

import cn.hutool.core.util.ObjectUtil;
import com.guyuan.manager.Entity.UserEntity;
import com.guyuan.manager.annotation.DoRequest;
import com.guyuan.manager.service.IUserService;
import com.guyuan.manager.util.HostHolder;
import com.guyuan.manager.util.TransferScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
@Controller
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @Autowired
    private IUserService userService;

    @Autowired
    private TransferScene transferScene;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void login(ActionEvent actionEvent) {
        String userName = usernameField.getText();
        String password = passwordField.getText();
        if (userName.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("输入无效！用户名和密码不能为空");
            alert.setContentText("请重新输入");
            alert.showAndWait();
            return;
        }
        UserEntity user = userService.getUserByName(userName);
        if (ObjectUtil.isEmpty(user)) {
            log.error("用户不存在");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("用户不存在！");
            alert.setContentText("请重新输入或联系管理员添加账号");
            alert.showAndWait();
            return;
        }
        if (!ObjectUtil.isEmpty(user) && user.getPassword().equals(password)) {
            try {
                // 登录成功，设置用户信息到HostHolder中
                HostHolder.setUsers(user);
                // 初始化历史记录
                TransferScene.init();
                transferScene.transfer((Stage)usernameField.getScene().getWindow(),"index");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else {
            // 弹出错误提示框
            log.error("用户名或密码错误");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("登录失败");
            alert.setContentText("用户名或密码错误");
            alert.showAndWait();
        }
    }
}
