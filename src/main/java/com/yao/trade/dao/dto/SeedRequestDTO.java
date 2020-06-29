package com.yao.trade.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

public class SeedRequestDTO {

    /**
     * 种子名字
     */
    private String name;

    /**
     * 价格
     */
    private String price;

    /**
     * 数量
     */
    private BigDecimal number;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 买还是卖
     * wts  wtb
     */
    private String sellOrBuy;

    /**
     * 备注
     */
    private String remark;


    /**
     * 操作码
     */
    private String operateNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSellOrBuy() {
        return sellOrBuy;
    }

    public void setSellOrBuy(String sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperateNumber() {
        return operateNumber;
    }

    public void setOperateNumber(String operateNumber) {
        this.operateNumber = operateNumber;
    }
}
