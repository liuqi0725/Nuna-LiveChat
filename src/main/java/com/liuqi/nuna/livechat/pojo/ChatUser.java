package com.liuqi.nuna.livechat.pojo;

import com.liuqi.nuna.livechat.other.tool.CommonTools;

import java.util.Date;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午3:37 2018/1/4
 */
public class ChatUser {

    private Integer id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private Integer confirmed;

    private Integer role_id;

    private Date created_at;

    private Date updated_at;

    private Integer permissions;

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
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

    /**
     * 验证密码
     * @param password 需要验证的密码串（未加密）
     * @return 是否匹配
     */
    public boolean validatePassword(String password){
        if(this.password.equalsIgnoreCase(CommonTools.Str2MD5(password,true))){
            return true;
        }
        return false;
    }
}
