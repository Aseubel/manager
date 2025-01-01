package com.guyuan.manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;

/**
 * @author yangzhiyao
 * @date 2024/12/4
 */
@SpringBootApplication
public class ManagerApplication extends Application {
	/**
	 * 任何地方都可以通过这个applicationContext获取springboot的上下文
	 */
	public static ConfigurableApplicationContext applicationContext;
	private static String[] args;

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL resource = getClass().getResource("/fxml/login.fxml");
		if (resource == null) {
			throw new Exception();
		}
		// 加载 fxml
		FXMLLoader loader = new FXMLLoader(resource);
		loader.setControllerFactory(param -> applicationContext.getBean(param));

		VBox root = loader.load();

		primaryStage.setTitle("团队管理系统");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}


	public static void main(String[] args) {
		ManagerApplication.args = args;
		launch(args);
	}

	@Override
	public void init() throws Exception {
		applicationContext = SpringApplication.run(ManagerApplication.class, args);
	}

	@Override
	public void stop() throws Exception {
		// 关闭springboot
		applicationContext.stop();
	}

}
