package com.vily.collect.req;

import lombok.Data;

@Data
public class BaseHeadReq extends BaseReq {

    private static final long serialVersionUID = 1L;

    /**
     * 登陆用户ID
     */
    private Long loginUserId;

    /**
     * Version
     */
    private Integer versionCode;

    /**
     * AppId
     */
    private Long applicationId;

}
