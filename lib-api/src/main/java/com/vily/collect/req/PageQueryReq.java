package com.vily.collect.req;

import lombok.Data;

@Data
public class PageQueryReq extends BaseReq {

    private Integer page;

    private Integer size;
}
