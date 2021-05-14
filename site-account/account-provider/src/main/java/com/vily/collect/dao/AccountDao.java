package com.vily.collect.dao;

import com.vily.collect.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountDao {
    int insert(Account record);

    Account getAccountById(@Param("id")Integer id);
}