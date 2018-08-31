package com.corkili.pa.dao.model;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import com.corkili.pa.dao.constant.State;
import com.corkili.pa.dao.exception.SerializationException;
import com.corkili.pa.dao.generate.protobuf.Model;
import com.corkili.pa.dao.util.DateTimeUtil;
import com.corkili.pa.validation.annotation.Validated;

@Validated
public abstract class BaseModel implements IModel {

    private Long id;

    private State state;

    private Date createTime;

    private Date updateTime;

    public BaseModel() {
    }

    public BaseModel(Long id, State state, Date createTime, Date updateTime) {
        this.id = id;
        this.state = state;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toJsonString() {
        return toJsonObject().toJSONString();
    }

    @Override
    public void fromJsonString(String jsonString) {
        fromJsonObject(JSONObject.parseObject(jsonString));
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("state", state.name());
        jsonObject.put("createTime", DateTimeUtil.formatDateTime(createTime));
        jsonObject.put("updateTime", DateTimeUtil.formatDateTime(updateTime));
        jsonObject.putAll(toJsonObject0());
        return jsonObject;
    }

    @Override
    public void fromJsonObject(JSONObject jsonObject) {
        try {
            this.id = jsonObject.getLong("id");
            this.state = State.valueOf(jsonObject.getString("state"));
            this.createTime = DateTimeUtil.parseDateTime(jsonObject.getString("createTime"));
            this.updateTime = DateTimeUtil.parseDateTime(jsonObject.getString("updateTime"));
            fromJsonObject0(jsonObject);
        } catch (Exception e) {
            throw new SerializationException("serialize from json failed", e);
        }
    }

    @Override
    public byte[] toPbBytes() {
        Model.Base.Builder baseBuilder = Model.Base.newBuilder();
        baseBuilder.setId(this.id);
        baseBuilder.setState(Model.State.forNumber(this.state.getState()));
        baseBuilder.setCreateTime(this.createTime.getTime());
        baseBuilder.setUpdateTime(this.updateTime.getTime());
        return toPbBytes0(baseBuilder.build());
    }

    @Override
    public void fromPbBytes(byte[] pbBytes) {
        Model.Base base = fromPbBytes0(pbBytes);
        this.id = base.getId();
        this.state = State.get(base.getState().getNumber());
        this.createTime = new Date(base.getCreateTime());
        this.updateTime = new Date(base.getUpdateTime());
    }

    protected abstract JSONObject toJsonObject0();

    protected abstract void fromJsonObject0(JSONObject jsonObject);

    protected abstract byte[] toPbBytes0(Model.Base base);

    protected abstract Model.Base fromPbBytes0(byte[] pbBytes);

}
