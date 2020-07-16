package com.yao.trade.dao.dto;

public class UserResponseDTO {

    private String id;

    private String serverName;

    private String roleName;

    private Integer roleLevel;

    private Integer vouch = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Integer getVouch() {
        return vouch;
    }

    public void setVouch(Integer vouch) {
        this.vouch = vouch;
    }
}
