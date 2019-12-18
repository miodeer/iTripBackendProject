package cn.ekgc.itrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * iTrip项目搜索功能Search Provider启动类
 * @author wang
 * @version 3.1.0
 * @since 2019-12-18
 */

@EnableEurekaClient
@SpringBootApplication
public class ItripSearchProviderStarter {
	public static void main(String[] args) {
		SpringApplication.run(ItripSearchProviderStarter.class, args);
	}
}
