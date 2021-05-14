package com.vily.collect.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * account
 * @author 
 */
@Data
public class Account implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private static final long serialVersionUID = 1L;
}