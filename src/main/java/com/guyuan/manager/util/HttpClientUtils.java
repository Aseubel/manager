package com.guyuan.manager.util;

import javafx.scene.control.Alert;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

/**
 * @author 杨之耀
 * @description 封装了HttpClient的常用方法
 * @date 2024/12/19
 */
@Component
@Slf4j
public class HttpClientUtils {

    public static Boolean doGet(String uri) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建一个HttpGet请求
            HttpGet request = new HttpGet("http://localhost:611/" + uri);
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info("响应内容: {}", responseBody);
            if (statusCode != 200) {
                log.error("请求失败，状态码: {}", statusCode);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误提示");
                alert.setHeaderText("请求服务器失败！状态码：" + statusCode + "，响应内容：" + responseBody);
                alert.setContentText("请稍后重试");
                alert.showAndWait();
                throw new Exception("请求失败，状态码: " + statusCode + "，响应内容：" + responseBody);
            }
            return true;
        } catch (HttpHostConnectException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("无法连接到服务器！");
            alert.setContentText("请稍后重试");
            alert.showAndWait();
            return false;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("未知错误！" + e.getMessage());
            alert.setContentText("请稍后重试");
            alert.showAndWait();
            return false;
        }
    }
}
