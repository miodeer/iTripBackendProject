package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * iTrip项目主功能Biz Provider启动类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-12
 */
@EnableEurekaClient
@SpringBootApplication
public class ItripBizConsumerStarter {
	public static void main(String[] args) {
		SpringApplication.run(ItripBizConsumerStarter.class, args);
	}
}
