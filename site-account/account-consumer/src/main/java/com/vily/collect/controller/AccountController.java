package com.vily.collect.controller;

import com.vily.collect.bean.ResultVO;
import com.vily.collect.service.FeignAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2021-05-13
 *  
 **/
@RestController
@RequestMapping("feign")
@CrossOrigin
@Slf4j
public class AccountController {

    @Autowired
    FeignAccountService mFeignAccountService;

    //http://localhost/order/consumer/payment/get/1
    @GetMapping("consumer/account/get/{id}")
    public ResultVO getAccountById(@PathVariable("id") Long id){

        System.out.println("发起请求：");

        return mFeignAccountService.getAccountById(id);
    }

    @GetMapping("consumer/test")
    public ResultVO test(){

        System.out.println("发起请求：");

        return new ResultVO(111,"sad",null);
    }


}
