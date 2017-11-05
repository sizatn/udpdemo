package com.sizatn.udpserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sizatn.udpserver.server.UdpServer;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		// 获取bean
		UdpServer udpServer = context.getBean(UdpServer.class);
		try {
			// 启动服务端
			udpServer.initServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
