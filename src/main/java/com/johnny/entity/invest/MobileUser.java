package com.johnny.entity.invest;

import com.google.common.base.MoreObjects;

/**
 * Created by johnson.yang on 2016/5/12.
 */
public class MobileUser {

    private Integer userid;
    private String pkey;
    private String uuid;
    private String phone;
    private String name;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(MobileUser.class)
                .add("userid", userid).add("pkey", pkey)
                .add("uuid", uuid).add("phone", phone).add("name", name).toString();
    }
}
