package com.corkili.pa.dao.model;

import java.util.Date;
import java.util.Objects;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.InvalidProtocolBufferException;

import com.corkili.pa.dao.constant.State;
import com.corkili.pa.dao.exception.SerializationException;
import com.corkili.pa.dao.generate.protobuf.Model;
import com.corkili.pa.dao.generate.protobuf.Model.Base;

public class DiaryBook extends BaseModel {

    private String diaryBookName;

    private String keywords;

    private String describe;

    private Long systemUserId;

    public DiaryBook() {
    }

    public DiaryBook(Long id, State state, Date createTime, Date updateTime, String diaryBookName,
                     String keywords, String describe, Long systemUserId) {
        super(id, state, createTime, updateTime);
        this.diaryBookName = diaryBookName;
        this.keywords = keywords;
        this.describe = describe;
        this.systemUserId = systemUserId;
    }

    public String getDiaryBookName() {
        return diaryBookName;
    }

    public void setDiaryBookName(String diaryBookName) {
        this.diaryBookName = diaryBookName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getSystemUserId() {
        return systemUserId;
    }

    public void setSystemUserId(Long systemUserId) {
        this.systemUserId = systemUserId;
    }

    @Override
    protected JSONObject toJsonObject0() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("diaryBookName", diaryBookName);
        jsonObject.put("keywords", keywords);
        jsonObject.put("describe", describe);
        jsonObject.put("systemUserId", systemUserId);
        return jsonObject;
    }

    @Override
    protected void fromJsonObject0(JSONObject jsonObject) {
        this.diaryBookName = jsonObject.getString("diaryBookName");
        this.keywords = jsonObject.getString("keywords");
        this.describe = jsonObject.getString("describe");
        this.systemUserId = jsonObject.getLong("systemUserId");
    }

    @Override
    protected byte[] toPbBytes0(Base base) {
        Model.DiaryBook.Builder diaryBookBuilder = Model.DiaryBook.newBuilder();
        diaryBookBuilder.setBase(base);
        diaryBookBuilder.setDiaryBookName(this.diaryBookName);
        diaryBookBuilder.setKeywords(this.keywords);
        diaryBookBuilder.setDescribe(this.describe);
        diaryBookBuilder.setSystemUserId(this.systemUserId);
        return diaryBookBuilder.build().toByteArray();
    }

    @Override
    protected Base fromPbBytes0(byte[] pbBytes) {
        try {
            Model.DiaryBook diaryBook = Model.DiaryBook.parseFrom(pbBytes);
            this.diaryBookName = diaryBook.getDiaryBookName();
            this.keywords = diaryBook.getKeywords();
            this.describe = diaryBook.getDescribe();
            this.systemUserId = diaryBook.getSystemUserId();
            return diaryBook.getBase();
        } catch (InvalidProtocolBufferException e) {
            throw new SerializationException("serialize from pb failed", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiaryBook diaryBook = (DiaryBook) o;
        return Objects.equals(diaryBookName, diaryBook.diaryBookName) &&
                Objects.equals(keywords, diaryBook.keywords) &&
                Objects.equals(describe, diaryBook.describe) &&
                Objects.equals(systemUserId, diaryBook.systemUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), diaryBookName, keywords, describe, systemUserId);
    }

    @Override
    public String toString() {
        return "DiaryBook{" +
                super.toString() +
                "diaryBookName='" + diaryBookName + '\'' +
                ", keywords='" + keywords + '\'' +
                ", describe='" + describe + '\'' +
                ", systemUserId=" + systemUserId +
                '}';
    }
}
