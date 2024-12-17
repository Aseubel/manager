package com.guyuan.manager.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
public class TestController implements Initializable {

    @FXML
    private ComboBox<String> comboBox1;
    @FXML
    private ComboBox<String> comboBox2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleComboBox1Action(ActionEvent actionEvent) {
    }
}
