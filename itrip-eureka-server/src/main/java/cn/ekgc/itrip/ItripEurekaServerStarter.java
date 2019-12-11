package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * iTrip项目注册中心Eureka Server启动类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-11
 */
@EnableEurekaServer
@SpringBootApplication
public class ItripEurekaServerStarter {
	public static void main(String[] args) {
		SpringApplication.run(ItripEurekaServerStarter.class, args);
	}
}
