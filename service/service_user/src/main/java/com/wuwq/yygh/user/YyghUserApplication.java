package com.wuwq.yygh.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.user
 * @ClassName:ServiceUserApplication
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-03-21 11:14
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.wuwq.yygh")
@EnableDiscoveryClient
public class YyghUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(YyghUserApplication.class,args);
    }
}
