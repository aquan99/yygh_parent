package com.wuwq.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.hosp
 * @ClassName:YyghHospApplication
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 21:55
 * @Version: 1.0
 */
@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.wuwq.yygh")
@EnableFeignClients
public class YyghHospApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyghHospApplication.class,args);
    }

}
