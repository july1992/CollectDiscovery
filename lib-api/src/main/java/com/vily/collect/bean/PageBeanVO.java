package com.vily.collect.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class PageBeanVO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * /总条目数
     */
    private int totalCount;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 当前页显示条目数
     */
    private int pageSize;

    /**
     *
     */
    private List<T> data;
}
