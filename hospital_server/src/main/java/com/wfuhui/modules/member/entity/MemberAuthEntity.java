package com.wfuhui.modules.member.entity;

import java.io.Serializable;
import java.util.Date;

public class MemberAuthEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //MemberID
    private Long memberId;
    //openid
    private String openid;
    //授权类型
    private String authType;
    //创建时间
    private Date createTime;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthType() {
        return authType;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
