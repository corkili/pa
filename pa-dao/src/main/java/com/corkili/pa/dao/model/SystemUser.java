package com.corkili.pa.dao.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import com.corkili.pa.dao.constant.State;
import com.corkili.pa.dao.exception.SerializationException;
import com.corkili.pa.dao.generate.protobuf.Model;
import com.corkili.pa.dao.generate.protobuf.Model.Base;
import com.corkili.pa.validation.annotation.Validated;

@Validated
public class SystemUser extends BaseModel {

    private String email;

    private String password;

    private String username;

    public SystemUser() {
    }

    public SystemUser(Long id, State state, Date createTime, Date updateTime,
                      String email, String password, String username) {
        super(id, state, createTime, updateTime);
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected JSONObject toJsonObject0() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", email);
        jsonObject.put("password", password);
        jsonObject.put("username", username);
        return jsonObject;
    }

    @Override
    protected void fromJsonObject0(JSONObject jsonObject) {
        this.email = jsonObject.getString("email");
        this.password = jsonObject.getString("password");
        this.username = jsonObject.getString("username");
    }

    @Override
    protected byte[] toPbBytes0(Base base) {
        Model.SystemUser.Builder systemUserBuilder = Model.SystemUser.newBuilder();
        systemUserBuilder.setBase(base);
        systemUserBuilder.setEmail(email);
        systemUserBuilder.setPassword(password);
        systemUserBuilder.setUsername(username);
        return systemUserBuilder.build().toByteArray();
    }

    @Override
    protected Base fromPbBytes0(byte[] pbBytes) {
        try {
            Model.SystemUser systemUser = Model.SystemUser.parseFrom(pbBytes);
            this.email = systemUser.getEmail();
            this.password = systemUser.getPassword();
            this.username = systemUser.getUsername();
            return systemUser.getBase();
        } catch (Exception e) {
            throw new SerializationException("serialize from pb failed", e);
        }
    }
}
