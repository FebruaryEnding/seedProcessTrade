package com.yao.trade.dao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SeedResponseDTO {

    private String id;

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
    private String unit;

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
     * 上架时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    /**
     * 密语
     */
    private String whisper;

    private String result;


    /**
     * 是不是腾讯服
     */
    private String  serverName;

    /**
     * 等级
     */
    private Integer level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void generateWhisperAndResult() {
        this.whisper = "@" + roleName + " " + (sellOrBuy.equals("wts") ? "buy" : "sell") + " <" + name + "> " + price;
        this.result = roleName + " " + sellOrBuy + " " + name + " " + price + "--" + createdTime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWhisper() {
        return whisper;
    }

    public void setWhisper(String whisper) {
        this.whisper = whisper;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
