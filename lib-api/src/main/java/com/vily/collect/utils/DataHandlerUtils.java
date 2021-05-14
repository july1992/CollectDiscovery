package com.vily.collect.utils;


import com.vily.collect.bean.PageBeanVO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 转换分页
 */
public class DataHandlerUtils {

    public static <T> PageBeanVO<T> handlePage(List<T> data, int page, int size, int totalCount){

        int pageSize = 0;
        if(data != null && data.size() > 0){
            pageSize = data.size();
        }

        int totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount/size+1;
        }

        PageBeanVO<T> pageBeanVO = new PageBeanVO<>();
        pageBeanVO.setPageNum(page);
        pageBeanVO.setPageSize(pageSize);
        pageBeanVO.setTotalCount(totalCount);
        pageBeanVO.setTotalPage(totalPage);
        pageBeanVO.setData(data);

        return pageBeanVO;
    }

    public static String sqlLikeStr(String data){
        return StringUtils.isEmpty(data) ? null : String.valueOf("%"+data+"%");
    }

    public static String sqlLikeByte(Byte data){
        return data == null ? "%%" : String.valueOf("%"+data+"%");

    }

}
