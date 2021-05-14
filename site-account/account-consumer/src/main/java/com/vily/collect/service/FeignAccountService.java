package com.vily.collect.service;

import com.vily.collect.bean.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(value = "collect-account-service")
public interface FeignAccountService {

    @GetMapping("account/get/{id}")
    ResultVO getAccountById(@PathVariable("id") Long id);

}
