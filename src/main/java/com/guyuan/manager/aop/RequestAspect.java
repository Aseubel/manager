package com.guyuan.manager.aop;

import com.guyuan.manager.annotation.DoRequest;
import javafx.scene.control.Alert;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 杨之耀
 * @description 发送请求切面
 * @date 2024/12/19
 */
@Component
@Slf4j
@Aspect
public class RequestAspect {

    @AfterReturning("@annotation(com.guyuan.manager.annotation.DoRequest)")
    public void doRequest(JoinPoint point) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        String url = "http://localhost:611/" + method.getAnnotation(DoRequest.class).value();
//        String url = "http://localhost:611";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建一个HttpGet请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            String responseBody = EntityUtils.toString(response.getEntity());
            if (statusCode != 200) {
                log.error("请求失败，状态码: {}", statusCode);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误提示");
                alert.setHeaderText("请求服务器失败！状态码：" + statusCode + "，响应内容：" + responseBody);
                alert.setContentText("请稍后重试");
                alert.showAndWait();
                throw new Exception("请求失败，状态码: " + statusCode);
            }
            log.info("响应内容: {}", responseBody);
        } catch (HttpHostConnectException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("无法连接到服务器！");
            alert.setContentText("请稍后重试");
            alert.showAndWait();
            throw e;
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误提示");
            alert.setHeaderText("未知错误！" + e.getMessage());
            alert.setContentText("请稍后重试");
            alert.showAndWait();
            throw e;
        }
    }
}
