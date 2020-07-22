package com.yao.trade.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_vouch")
public class VouchEntity {


    @Id
    @GeneratedValue(
            generator = "system-uuid"
    )
    @GenericGenerator(
            name = "system-uuid",
            strategy = "uuid"
    )
    private String id;

    private String content;

    private String beVouchedUserId;

    private String beVouchedUserName;

    private String vouchUserId;

    private String vouchUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBeVouchedUserId() {
        return beVouchedUserId;
    }

    public void setBeVouchedUserId(String beVouchedUserId) {
        this.beVouchedUserId = beVouchedUserId;
    }

    public String getBeVouchedUserName() {
        return beVouchedUserName;
    }

    public void setBeVouchedUserName(String beVouchedUserName) {
        this.beVouchedUserName = beVouchedUserName;
    }

    public String getVouchUserId() {
        return vouchUserId;
    }

    public void setVouchUserId(String vouchUserId) {
        this.vouchUserId = vouchUserId;
    }

    public String getVouchUserName() {
        return vouchUserName;
    }

    public void setVouchUserName(String vouchUserName) {
        this.vouchUserName = vouchUserName;
    }
}
