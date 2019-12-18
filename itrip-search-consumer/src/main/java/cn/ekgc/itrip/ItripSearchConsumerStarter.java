package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * iTrip项目搜索功能Search Consumer启动类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-18
 */
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ItripSearchConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(ItripSearchConsumerStarter.class, args);
	}
}
