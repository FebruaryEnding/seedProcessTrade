package com.yao.trade.dao.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

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
    private BigDecimal price;

    /**
     * 单位
     */
    @ApiModelProperty("单位 e 或者 c 选择框")
    private String unit;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private BigDecimal number;

    /**
     * 角色名
     */
    @ApiModelProperty("角色名")
    private String roleName;

    /**
     * 买还是卖
     * wts  wtb
     */
    @ApiModelProperty("买还是卖")
    private String sellOrBuy;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;


    /**
     * 操作码
     */
    @ApiModelProperty("操作码")
    private String operateNumber;


    /**
     * 是不是腾讯服
     */
    @ApiModelProperty("服务器 （国际服/国服）二选一")
    private String  serverName;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
