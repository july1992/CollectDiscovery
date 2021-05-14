package com.vily.collect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @className: OrderNacosApplication80
 * @description:
 * @author: zmm
 * @create: 2020-06-10 17:19
 */

@SpringBootApplication
@EnableDiscoveryClient
public class NacosOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain.class,args);
    }
}
