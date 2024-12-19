package com.guyuan.manager;

import javafx.scene.control.Alert;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ManagerApplicationTests {

	@Test
	void contextLoads() {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			// 创建一个HttpGet请求
			HttpGet request = new HttpGet("http://localhost:611/auth");
			HttpResponse response = httpClient.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				System.out.println("请求失败，状态码：" + statusCode);
			}
			System.out.println(response);
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("响应内容: " + responseBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
