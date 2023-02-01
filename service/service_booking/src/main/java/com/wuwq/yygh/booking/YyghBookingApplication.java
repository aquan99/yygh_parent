package com.wuwq.yygh.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.booking
 * @ClassName:YyghBookingApplication
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-23 9:24
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.wuwq.yygh")
@EnableDiscoveryClient
@EnableFeignClients
public class YyghBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(YyghBookingApplication.class,args);
    }
}
