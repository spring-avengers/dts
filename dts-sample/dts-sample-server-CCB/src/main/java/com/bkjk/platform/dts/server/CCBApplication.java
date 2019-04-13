
package com.bkjk.platform.dts.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = {"com.bkjk.platform.dts.server.mapper"})
public class CCBApplication {
    public static void main(String[] args) {
        SpringApplication.run(CCBApplication.class, args);
    }
}
