package com.corkili.pa.dao.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public interface IModel extends Serializable {

    String toJsonString();

    void fromJsonString(String jsonString);

    JSONObject toJsonObject();

    void fromJsonObject(JSONObject jsonObject);

    byte[] toPbBytes();

    void fromPbBytes(byte[] pbBytes);

}
