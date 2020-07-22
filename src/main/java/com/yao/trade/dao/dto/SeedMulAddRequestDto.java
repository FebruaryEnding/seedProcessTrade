package com.yao.trade.dao.dto;

import java.util.List;

public class SeedMulAddRequestDto {

    private List<SeedMulAddItemRequestDto> add;

    private List<SeedMulAddItemRequestDto> removeAndAdd;

    private List<SeedMulAddItemRequestDto> addLuck;

    private List<SeedMulAddItemRequestDto> remove;

    private String roleName;

    private String sellOrBuy;



    private String serverName;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSellOrBuy() {
        return sellOrBuy;
    }

    public void setSellOrBuy(String sellOrBuy) {
        this.sellOrBuy = sellOrBuy;
    }

    public List<SeedMulAddItemRequestDto> getAdd() {
        return add;
    }

    public void setAdd(List<SeedMulAddItemRequestDto> add) {
        this.add = add;
    }

    public List<SeedMulAddItemRequestDto> getRemoveAndAdd() {
        return removeAndAdd;
    }

    public void setRemoveAndAdd(List<SeedMulAddItemRequestDto> removeAndAdd) {
        this.removeAndAdd = removeAndAdd;
    }

    public List<SeedMulAddItemRequestDto> getAddLuck() {
        return addLuck;
    }

    public void setAddLuck(List<SeedMulAddItemRequestDto> addLuck) {
        this.addLuck = addLuck;
    }

    public List<SeedMulAddItemRequestDto> getRemove() {
        return remove;
    }

    public void setRemove(List<SeedMulAddItemRequestDto> remove) {
        this.remove = remove;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
