package com.yao.trade.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 周杨
 * @Date: 2018年7月23日
 * @Description: 分页信息封装
 */
public class PageResult implements Serializable {

    public PageResult() {
    }

    //数据
    private List rows = new ArrayList();

    //总页数
    private Integer totalPage;

    //总数
    private Long totalCount;

    //当前页
    private Integer page;

    private PageResult(List rows, Integer totalPage, Long totalCount, Integer page) {
        this.rows = rows;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.page = page;
    }

    public static PageResult newInstace(List rows, Integer totalPage, Long totalCount, Integer page) {
        return new PageResult(rows, totalPage, totalCount, page);
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List getRows() {
        return rows;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getWebPage() {
        return getPage() + 1;
    }
}
