package com.yao.trade.dao.dto;

public class RoleItem {

//    {
//        "name":"坚强的鹅",
//            "league":"永久",
//            "classId":2,
//            "ascendancyClass":0,
//            "class":"Ranger",
//            "level":7,
//            "experience":29277
//    }


    private String name;

    private String league;

    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
