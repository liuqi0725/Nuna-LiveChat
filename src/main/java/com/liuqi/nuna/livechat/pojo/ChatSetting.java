package com.liuqi.nuna.livechat.pojo;

import java.util.Date;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午8:26 2018/1/3
 */
public class ChatSetting {


    private Integer id;

    private String key_name;

    private String key_value;

    private String key_default;

    private String key_desc;

    private Integer key_status;

    private Date created_at;

    private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public String getKey_value() {
        return key_value;
    }

    public void setKey_value(String key_value) {
        this.key_value = key_value;
    }

    public String getKey_default() {
        return key_default;
    }

    public void setKey_default(String key_default) {
        this.key_default = key_default;
    }

    public String getKey_desc() {
        return key_desc;
    }

    public void setKey_desc(String key_desc) {
        this.key_desc = key_desc;
    }

    public Integer getKey_status() {
        return key_status;
    }

    public void setKey_status(Integer key_status) {
        this.key_status = key_status;
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
