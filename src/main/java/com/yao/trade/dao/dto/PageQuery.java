package com.yao.trade.dao.dto;

import java.io.Serializable;

/**
 * @Author: 周杨
 * @Date: 2018年7月23日
 * @Description: 分页查询
 */
public class PageQuery implements Serializable {

    public static Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 显示条数
     */
    private Integer pageSize;

    public PageQuery() {
    }

    public PageQuery(Integer page, Integer pageSize) {
        this.setPage(page);
        this.setPageSize(pageSize);
    }

    public Integer getPage() {
        if (page <= 1) {
            return 0;
        }
        return page - 1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return PageQuery.DEFAULT_PAGE_SIZE;
        } else if (pageSize <= 0) {
            return PageQuery.DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


}
