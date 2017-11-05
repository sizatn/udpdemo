package com.sizatn.udpclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sizatn.udpclient.client.UdpClient;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		
		// 获取bean
		UdpClient udpClient = context.getBean(UdpClient.class);
		try {
			// 发送信息包
			udpClient.sendPackage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
