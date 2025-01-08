package com.guyuan.manager.controller;

import ch.qos.logback.core.util.StringUtil;
import cn.hutool.core.util.ObjectUtil;
import com.guyuan.manager.Entity.UserEntity;
import com.guyuan.manager.exception.MyException;
import com.guyuan.manager.service.ITeamService;
import com.guyuan.manager.service.IUserService;
import com.guyuan.manager.util.HostHolder;
import com.guyuan.manager.util.TransferType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author 杨之耀
 * @description 用户管理页面控制器
 * @date 2024/12/4
 */
@Slf4j
@Controller
public class UserController implements Initializable {

    @FXML private Label nameFieldV;
    @FXML private Label genderV;
    @FXML private Label entryTimeFieldV;
    @FXML private ChoiceBox<String> teamChoiceBoxV;
    @FXML private Label idFieldV;
    @FXML private Label phoneFieldV;
    @FXML private Label emailFieldV;
    @FXML private Label gradeFieldV;
    @FXML private Label studentIdFieldV;
    @FXML private Label majorFieldV;
    @FXML private TextArea experienceAreaV;
    @FXML private TextArea currentStatusAreaV;

    @FXML private TextField nameField;
    @FXML private ChoiceBox<String> genderChoiceBox;
//    @FXML private TextField entryTimeField;
    @FXML private DatePicker timePicker;
    @FXML private Label selectedTimeLabel;
    @FXML private ChoiceBox<String> teamChoiceBox;
    @FXML private TextField idField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private TextField gradeField;
    @FXML private TextField studentIdField;
    @FXML private TextField majorField;
    @FXML private TextArea experienceArea;
    @FXML private TextArea currentStatusArea;
    @FXML private Label memberIdField;
    @FXML private Label addResultLabel;

    @FXML
    private TextField searchUsernameField;

    @FXML private ComboBox<String> comboBoxt;
    @FXML private ComboBox<String> comboBox1;
    @FXML private ComboBox<String> comboBox2;
    @FXML private ComboBox<String> comboBox3;
    @FXML private ComboBox<String> comboBox4;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITeamService teamService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] path = url.getPath().split("/");
        String fileName = path[path.length - 1];
        if (fileName.equals("modifyUser.fxml")) {
            ObservableList<String> options1 = FXCollections.observableArrayList("研发组", "设计组", "产品组","团队负责人");
            comboBoxt.setItems(options1);
            // 监听时间选择事件
            timePicker.setOnAction(event -> {
                LocalDate selectedTime = timePicker.getValue();
                if (selectedTime != null) {
                    selectedTimeLabel.setText("选定的时间: " + selectedTime.toString());
                } else {
                    selectedTimeLabel.setText("未选定时间");
                }
            });
        } else if (fileName.equals("addUser.fxml")) {
            ObservableList<String> options1 = FXCollections.observableArrayList("研发组", "设计组", "产品组","团队负责人");
            comboBoxt.setItems(options1);
        }
    }

    /**
     * 搜索用户信息，修改用户时调用
     * @param actionEvent
     * @date 2024/12/6
     */
    @FXML
    public void handleSearch(ActionEvent actionEvent) {
        try {
            String username = searchUsernameField.getText();
            if (username.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("错误提示");
                alert.setHeaderText("输入无效！用户名和密码不能为空");
                alert.setContentText("请重新输入");
                alert.showAndWait();
                return;
            }
            log.info("查询用户，{}", username);
            UserEntity user = userService.getUserByName(username);
            memberIdField.setText("");
            nameField.setText("");
            genderChoiceBox.getSelectionModel().clearSelection();
            timePicker.setValue(null);
//            entryTimeField.setText("");
            teamChoiceBox.getItems().clear();
            idField.setText("");
            phoneField.setText("");
            emailField.setText("");
            gradeField.setText("");
            studentIdField.setText("");
            majorField.setText("");
            experienceArea.setText("");
            currentStatusArea.setText("");
            log.info("查询到用户信息,{}",user);
            if (!ObjectUtil.isEmpty(user)) {
                user.init();

                memberIdField.setText(user.getUserId());
                nameField.setText(user.getUserName());
                genderChoiceBox.setValue(TransferType.convertGender(user.getGender()));
                timePicker.setValue(user.getEntryTime());
//                entryTimeField.setText(TransferType.convertTime(user.getEntryTime()));
                teamChoiceBox.getItems().addAll(user.getPositionList());
                idField.setText(user.getIdCard());
                phoneField.setText(user.getPhone());
                emailField.setText(user.getEmail());
                gradeField.setText(user.getGrade().toString());
                studentIdField.setText(user.getStudentId());
                majorField.setText(user.getMajor());
                experienceArea.setText(user.getExperience());
                currentStatusArea.setText(user.getCurrentStatus());
            } else {
                log.error("用户不存在！username：{}", username);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误提示");
                alert.setHeaderText("用户不存在！");
                alert.setContentText("请重新输入或联系管理员添加账号");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            log.error("查询用户失败！",e);
        }
    }

    /**
     * 搜索用户信息，查看用户信息时调用
     * @param actionEvent
     */
    @FXML
    public void handleSearchV(ActionEvent actionEvent) {
        try {
            String username = searchUsernameField.getText();
            if (username.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("错误提示");
                alert.setHeaderText("输入无效！用户名和密码不能为空");
                alert.setContentText("请重新输入");
                alert.showAndWait();
                return;
            }
            log.info("查询用户，{}", username);
            UserEntity user = userService.getUserByName(username);
            nameFieldV.setText("");
            genderV.setText("");
            entryTimeFieldV.setText("");
//            Optional.ofNullable(teamChoiceBoxV)
//                    .ifPresent(choiceBox -> choiceBox.getItems().clear());
            teamChoiceBoxV.getItems().clear();
            idFieldV.setText("");
            phoneFieldV.setText("");
            emailFieldV.setText("");
            gradeFieldV.setText("");
            studentIdFieldV.setText("");
            majorFieldV.setText("");
            experienceAreaV.setText("");
            currentStatusAreaV.setText("");
            log.info("查询到用户信息,{}",user);
            if (!ObjectUtil.isEmpty(user)) {
                user.init();

                nameFieldV.setText(user.getUserName());
                genderV.setText(TransferType.convertGender(user.getGender()));
                entryTimeFieldV.setText(user.getEntryTime().toString());
//                entryTimeFieldV.setText(TransferType.convertTime(user.getEntryTime()));
//                Optional.ofNullable(teamChoiceBoxV)
//                        .ifPresent(choiceBox -> choiceBox.getItems().addAll(user.getPositionList()));
                teamChoiceBoxV.getItems().addAll(user.getPositionList());
                idFieldV.setText(user.getIdCard());
                phoneFieldV.setText(user.getPhone());
                emailFieldV.setText(user.getEmail());
                gradeFieldV.setText(user.getGrade().toString());
                studentIdFieldV.setText(user.getStudentId());
                majorFieldV.setText(user.getMajor());
                experienceAreaV.setText(user.getExperience());
                currentStatusAreaV.setText(user.getCurrentStatus());
            } else {
                log.error("用户不存在！username：{}", username);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误提示");
                alert.setHeaderText("用户不存在！");
                alert.setContentText("请重新输入或联系管理员添加账号");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            log.error("查询用户失败！",e);
        }
    }

    @FXML
    public void handleReset(ActionEvent actionEvent) {
        handleSearch(actionEvent);
    }

    /**
     * 保存修改的用户信息
     * @param actionEvent
     * @date 2024/12/7
     */
    @FXML
    public void handleSave(ActionEvent actionEvent) {
        try {
            if (StringUtil.isNullOrEmpty(memberIdField.getText())) {
                throw new MyException("请先查询用户信息！");
            }
            String userId = HostHolder.getUsers().getUserId();
//            String entryTime = entryTimeField.getText().isEmpty() ? LocalDate.now().toString() : entryTimeField.getText();
            UserEntity user = UserEntity.builder()
                    .userId(memberIdField.getText())
                    .userName(nameField.getText())
                    .gender(TransferType.convertGender(Optional.ofNullable(genderChoiceBox.getValue()).orElse("未选择")))
                    .entryTime(timePicker.getValue())
//                    .entryTime(TransferType.convertTime(entryTime))
                    .idCard(idField.getText())
                    .phone(phoneField.getText())
                    .email(emailField.getText())
                    .grade(gradeField.getText().isEmpty() ? 0 : Integer.parseInt(gradeField.getText()))
                    .studentId(studentIdField.getText())
                    .major(majorField.getText())
                    .experience(experienceArea.getText())
                    .currentStatus(currentStatusArea.getText())
                    .positionList(teamChoiceBox.getItems())
                    .build();
            userService.modifyUserInfo(user, userId);
        } catch (Exception e) {
            log.error("保存用户失败！",e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("保存失败！");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }

    @FXML
    private void handleComboBox1Action() {
        String selectedOption = comboBoxt.getValue();
        // 根据第一个下拉框的选值更新第二个下拉框的内容
        ObservableList<String> options1 = switch (selectedOption) {
            case "研发组" -> FXCollections.observableArrayList("前端组", "后端组");
            case "设计组" -> FXCollections.observableArrayList("实习设计师", "设计组负责人");
            case "产品组" -> FXCollections.observableArrayList("产品经理", "产品组负责人");
            default -> FXCollections.observableArrayList();
        };
        comboBox1.setItems(options1);
        comboBox1.setDisable(options1.isEmpty());
    }
    @FXML
    private void handleComboBox2Action() {
        String selectedOption = comboBoxt.getValue();
        // 根据第一个下拉框的选值更新第二个下拉框的内容
        ObservableList<String> options2 = switch (selectedOption) {
            case "研发组" -> FXCollections.observableArrayList("前端组", "后端组");
            case "设计组" -> FXCollections.observableArrayList("实习设计师", "设计组负责人");
            case "产品组" -> FXCollections.observableArrayList("产品经理", "产品组负责人");
            default -> FXCollections.observableArrayList();
        };
        comboBox1.setItems(options2);
        comboBox1.setDisable(options2.isEmpty());
    }
    @FXML
    private void handleComboBox3Action() {
        String selectedOption = comboBoxt.getValue();
        // 根据第一个下拉框的选值更新第二个下拉框的内容
        ObservableList<String> options3 = switch (selectedOption) {
            case "研发组" -> FXCollections.observableArrayList("前端组", "后端组");
            case "设计组" -> FXCollections.observableArrayList("实习设计师", "设计组负责人");
            case "产品组" -> FXCollections.observableArrayList("产品经理", "产品组负责人");
            default -> FXCollections.observableArrayList();
        };
        comboBox1.setItems(options3);
        comboBox1.setDisable(options3.isEmpty());
    }
    @FXML
    private void handleComboBox4Action() {
        String selectedOption = comboBoxt.getValue();
        // 根据第一个下拉框的选值更新第二个下拉框的内容
        ObservableList<String> options4 = switch (selectedOption) {
            case "研发组" -> FXCollections.observableArrayList("前端组", "后端组");
            case "设计组" -> FXCollections.observableArrayList("实习设计师", "设计组负责人");
            case "产品组" -> FXCollections.observableArrayList("产品经理", "产品组负责人");
            default -> FXCollections.observableArrayList();
        };
        comboBox1.setItems(options4);
        comboBox1.setDisable(options4.isEmpty());
    }
    /**
     * 删除用户的团队/职位
     * @param actionEvent
     * @date 2024/12/7
     */
    @FXML
    public void handleRemovePosition(ActionEvent actionEvent) {
        try {
            // 获取选中的项
            String selectedPosition = teamChoiceBox.getSelectionModel().getSelectedItem();
            // 清除选择
            teamChoiceBox.getSelectionModel().clearSelection();
            // 若选项不为空，则移除该选项
            if (selectedPosition != null) {
                teamChoiceBox.getItems().remove(selectedPosition);
            }
        } catch (Exception e) {
            log.error("删除团队失败！",e);
        }
    }

    @FXML
    public void handlePlusPosition(ActionEvent actionEvent) {
        // 获取当前选择并添加到团队/职位列表
        String opt = comboBoxt.getValue(), op1 = comboBox1.getValue(), op2 = comboBox2.getValue(), op3 = comboBox3.getValue();
        // 获取当前选择并添加到团队/职位列表
        StringBuilder newPosition = new StringBuilder();
        if (comboBoxt.getValue() != null) {
            newPosition.append(comboBoxt.getValue());
            if (comboBox1.getValue() != null) {
                newPosition.append("-").append(comboBox1.getValue());
                if (comboBox2.getValue() != null) {
                    newPosition.append("-").append(comboBox2.getValue());
                    if (comboBox3.getValue() != null) {
                        newPosition.append("-").append(comboBox3.getValue());
                        if (comboBox4.getValue() != null) {
                            newPosition.append("-").append(comboBox4.getValue());
                        }
                    }
                }
            }
        }
        teamChoiceBox.getItems().add(newPosition.toString());

        // 清空选项
        comboBoxt.setValue(null);
        comboBox1.setValue(null);
        comboBox2.setValue(null);
        comboBox3.setValue(null);
        comboBox4.setValue(null);
        // 设置为不可用
        comboBox1.setDisable(true);
        comboBox2.setDisable(true);
        comboBox3.setDisable(true);
        comboBox4.setDisable(true);
    }

    @FXML
    public void handleAddUser(ActionEvent actionEvent) {
        try {
            String userId = HostHolder.getUsers().getUserId();
//            String entryTime = entryTimeField.getText().isEmpty() ? LocalDate.now().toString() : entryTimeField.getText();
            UserEntity user = UserEntity.builder()
                    .userName(nameField.getText())
                    .gender(TransferType.convertGender(Optional.ofNullable(genderChoiceBox.getValue()).orElse("未选择")))
                    .entryTime(timePicker.getValue())
                    .idCard(idField.getText())
                    .phone(phoneField.getText())
                    .email(emailField.getText())
                    .grade(gradeField.getText().isEmpty() ? 0 : Integer.parseInt(gradeField.getText()))
                    .studentId(studentIdField.getText())
                    .major(majorField.getText())
                    .experience(experienceArea.getText())
                    .currentStatus(currentStatusArea.getText())
                    .positionList(teamChoiceBox.getItems())
                    .build();
            userService.AddUser(user, userId);
            addResultLabel.setText("添加用户 " + nameField.getText() + " 成功！");
        } catch (Exception e) {
            log.error("保存用户失败！",e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("添加用户失败！");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }
}
