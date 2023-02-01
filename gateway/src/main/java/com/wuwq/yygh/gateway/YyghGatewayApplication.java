package com.wuwq.yygh.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ProjectName:yygh_parent
 * @Package:com.wuwq.yygh.gateway
 * @ClassName:YyghGatewayApplication
 * @Author:Wuwq
 * @Description:
 * @Date: 2022-04-08 15:17
 * @Version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class YyghGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(YyghGatewayApplication.class, args);
    }

}
