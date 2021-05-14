package com.vily.collect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2021-05-13
 *  
 **/
@SpringBootApplication
@EnableFeignClients
public class AccountConsumerMain {

    public static void main(String[] args) {

        SpringApplication.run(AccountConsumerMain.class,args);
    }
}
