package com.vily.collect.controller;
import com.vily.collect.bean.Account;
import com.vily.collect.bean.ResultVO;
import com.vily.collect.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2021-04-20
 *  
 **/
@RestController
@CrossOrigin
@RequestMapping("account")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService mAccountService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("create")
    public ResultVO create(@RequestBody Account account){

        int i = mAccountService.create(account);

        if(i>0){
            return new ResultVO(200,"插入成功",i);
        }else{
            return new ResultVO(444,"插入失败",null);
        }
    }

    @GetMapping("get/{id}")
    public ResultVO getAccountById(@PathVariable("id") Integer id){

        Account account = mAccountService.getAccountById(id);
        if (account != null){
            ResultVO commonResult = new ResultVO(200, "查询成功,端口号:"+serverPort, account);
            return commonResult;
        }else {
            return new ResultVO(444,"数据不存在",null);
        }
    }
}