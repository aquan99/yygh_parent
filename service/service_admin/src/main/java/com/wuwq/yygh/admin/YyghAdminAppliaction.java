package com.wuwq.yygh.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.admin
 * @ClassName:YyghAdminAppliaction
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-22 16:16
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.wuwq.yygh")
@EnableDiscoveryClient
public class YyghAdminAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(YyghAdminAppliaction.class,args);
    }
}
