package com.vily.collect.service.impl;

import com.vily.collect.bean.Account;
import com.vily.collect.dao.AccountDao;
import com.vily.collect.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2021-05-13
 *  
 **/
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao mAccountDao;

    @Override
    public int create(Account account) {
        return mAccountDao.insert(account);
    }

    @Override
    public Account getAccountById(Integer id) {
        return mAccountDao.getAccountById(id);
    }
}
