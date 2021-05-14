package com.vily.collect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController
{
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    //http://localhost:83/consumer/payment/nacos/1
    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String accountInfo(@PathVariable("id") Long id)
    {
        return restTemplate.getForObject(serverURL+"/account/get/"+id,String.class);
    }
    @GetMapping(value = "/consumer/config/test")
    public String configTest()
    {
        return restTemplate.getForObject(serverURL+"/config/test",String.class);
    }
}
