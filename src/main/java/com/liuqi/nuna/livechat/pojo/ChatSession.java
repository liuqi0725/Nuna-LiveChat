package com.liuqi.nuna.livechat.pojo;

import java.util.Date;

/**
 * @Author : alexliu
 * @Description : something do..
 * @Date : Create at 下午2:58 2018/1/3
 */
public class ChatSession {

    private Integer userId;

    private Integer agentId;

    private Date created_at;

    private Date closed_at;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Date closed_at) {
        this.closed_at = closed_at;
    }
}
