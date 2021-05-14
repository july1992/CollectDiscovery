package com.vily.collect.service;

import com.vily.collect.bean.Account;
import org.apache.ibatis.annotations.Param;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2021-05-13
 *  
 **/

public interface AccountService {

    int create(Account account);
    Account getAccountById(@Param("id") Integer id);
}
