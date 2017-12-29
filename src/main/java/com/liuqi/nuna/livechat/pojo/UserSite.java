package com.liuqi.nuna.livechat.pojo;

import java.util.Date;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午4:51 2017/12/29
 */
public class UserSite {

    private Integer id;

    private Integer uid;

    private String site_domain;

    private String site_key;

    private Integer status;

    private Date stop_at;

    private Date created_at;

    private Date updated_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSite_domain() {
        return site_domain;
    }

    public void setSite_domain(String site_domain) {
        this.site_domain = site_domain;
    }

    public String getSite_key() {
        return site_key;
    }

    public void setSite_key(String site_key) {
        this.site_key = site_key;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStop_at() {
        return stop_at;
    }

    public void setStop_at(Date stop_at) {
        this.stop_at = stop_at;
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
