package com.guyuan.manager.util;

import cn.hutool.core.collection.CollectionUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.guyuan.manager.ManagerApplication.applicationContext;

/**
 * @author yangzhiyao
 * @description 转移场景
 * @date 2024/12/4
 */
@Slf4j
@Component
public class TransferScene {

    private static final ThreadLocal<List<String>> scenes = new ThreadLocal<>();
    private static final ThreadLocal<Integer> sceneIndex = new ThreadLocal<>();

    public static List<String> getScenes() {
        return scenes.get();
    }

    public static Integer getSceneIndex() {
        return sceneIndex.get();
    }

    public static void init() {
        scenes.set(new ArrayList<>());
        sceneIndex.set(0);
    }

    public static String getNowScene() {
        return scenes.get().get(sceneIndex.get() - 1);
    }

    public void lastScene(Pane contentBox) {
        Integer index = sceneIndex.get();
        if (index == 0 || index == 1) {
            return;
        }
        index-=1;
        sceneIndex.set(index);
        loadScene(contentBox, scenes.get().get(index-1), false);
    }

    public void nextScene(Pane contentBox) {
        Integer index = sceneIndex.get();
        if (index == scenes.get().size()) {
            return;
        }
        index+=1;
        sceneIndex.set(index);
        loadScene(contentBox, scenes.get().get(index-1), false);
    }

    public static void clear() {
        scenes.remove();
        sceneIndex.remove();
    }

    /**
     * 封装一个方法，便于调用，主要是从登录页到主页，还有退出登录
     * @param stage
     * @param fxmlName
     * @throws Exception
     */
    public void transfer(Stage stage, String fxmlName) throws Exception {
        URL resource = getClass().getResource("/fxml/" + fxmlName + ".fxml");
        Optional.ofNullable(resource).orElseThrow(() -> new Exception("FXML文件不存在"));

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(param -> applicationContext.getBean(param));

        VBox root = loader.load();

        stage.setTitle("团队管理系统");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * 加载场景
     * @param contentBox
     * @param fxmlFile
     * @param type true为记录历史，false为不记录，为系统操作
     */
    public void loadScene(Pane contentBox, String fxmlFile, Boolean type) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlFile+ ".fxml"));
            loader.setControllerFactory(param -> applicationContext.getBean(param));
            Pane newScene = loader.load();
            contentBox.getChildren().setAll(newScene);
            if (type) {
                scenes.get().add(fxmlFile);
                sceneIndex.set(scenes.get().size());
            }
        } catch (IOException e) {
            log.error("加载fxml文件失败: {}", fxmlFile, e);
        }
    }
}
