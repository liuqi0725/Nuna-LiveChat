package com.liuqi.nuna.livechat.pojo;

import java.util.Date;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:38 2018/1/4
 */
public class ChatUserRole {

    private Integer id;

    private String name;

    private Integer permissions;

    private Integer user_default;

    private String desc;

    private Date created_at;

    private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public Integer getUser_default() {
        return user_default;
    }

    public void setUser_default(Integer user_default) {
        this.user_default = user_default;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
