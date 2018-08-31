package com.corkili.pa.dao.model;

import java.util.Date;
import java.util.Objects;

import com.alibaba.fastjson.JSONObject;

import com.corkili.pa.dao.constant.State;
import com.corkili.pa.dao.exception.SerializationException;
import com.corkili.pa.dao.generate.protobuf.Model;
import com.corkili.pa.dao.generate.protobuf.Model.Base;

public class Diary extends BaseModel {

    private String diaryTitle;

    private long diaryTime;

    private String keywords;

    private String uri;

    private Long diaryBookId;

    private Long systemUserId;

    public Diary() {
    }

    public Diary(Long id, State state, Date createTime, Date updateTime, String diaryTitle, long diaryTime,
                 String keywords, String uri, Long diaryBookId, Long systemUserId) {
        super(id, state, createTime, updateTime);
        this.diaryTitle = diaryTitle;
        this.diaryTime = diaryTime;
        this.keywords = keywords;
        this.uri = uri;
        this.diaryBookId = diaryBookId;
        this.systemUserId = systemUserId;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public long getDiaryTime() {
        return diaryTime;
    }

    public void setDiaryTime(long diaryTime) {
        this.diaryTime = diaryTime;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Long getDiaryBookId() {
        return diaryBookId;
    }

    public void setDiaryBookId(Long diaryBookId) {
        this.diaryBookId = diaryBookId;
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
        jsonObject.put("diaryTitle", this.diaryTitle);
        jsonObject.put("diaryTime", this.diaryTime);
        jsonObject.put("keywords", this.keywords);
        jsonObject.put("uri", this.uri);
        jsonObject.put("diaryBookId", this.diaryBookId);
        jsonObject.put("systemUserId", this.systemUserId);
        return jsonObject;
    }

    @Override
    protected void fromJsonObject0(JSONObject jsonObject) {
        this.diaryTitle = jsonObject.getString("diaryTitle");
        this.diaryTime = jsonObject.getLong("diaryTime");
        this.keywords = jsonObject.getString("keywords");
        this.uri = jsonObject.getString("uri");
        this.diaryBookId = jsonObject.getLong("diaryBookId");
        this.systemUserId = jsonObject.getLong("systemUserId");
    }

    @Override
    protected byte[] toPbBytes0(Base base) {
        Model.Diary.Builder diaryBuilder = Model.Diary.newBuilder();
        diaryBuilder.setBase(base);
        diaryBuilder.setDiaryTitle(this.diaryTitle);
        diaryBuilder.setDiaryTime(this.diaryTime);
        diaryBuilder.setKeywords(this.keywords);
        diaryBuilder.setUri(this.uri);
        diaryBuilder.setDiaryBookId(this.diaryBookId);
        diaryBuilder.setSystemUserId(this.systemUserId);
        return diaryBuilder.build().toByteArray();
    }

    @Override
    protected Base fromPbBytes0(byte[] pbBytes) {
        try {
            Model.Diary diary = Model.Diary.parseFrom(pbBytes);
            this.diaryTitle = diary.getDiaryTitle();
            this.diaryTime = diary.getDiaryTime();
            this.keywords = diary.getKeywords();
            this.uri = diary.getUri();
            this.diaryBookId = diary.getDiaryBookId();
            this.systemUserId = diary.getSystemUserId();
            return diary.getBase();
        } catch (Exception e) {
            throw new SerializationException("serialize from pb failed", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Diary diary = (Diary) o;
        return diaryTime == diary.diaryTime &&
                Objects.equals(diaryTitle, diary.diaryTitle) &&
                Objects.equals(keywords, diary.keywords) &&
                Objects.equals(uri, diary.uri) &&
                Objects.equals(diaryBookId, diary.diaryBookId) &&
                Objects.equals(systemUserId, diary.systemUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), diaryTitle, diaryTime, keywords, uri, diaryBookId, systemUserId);
    }

    @Override
    public String toString() {
        return "Diary{" +
                super.toString() +
                "diaryTitle='" + diaryTitle + '\'' +
                ", diaryTime=" + diaryTime +
                ", keywords='" + keywords + '\'' +
                ", uri='" + uri + '\'' +
                ", diaryBookId=" + diaryBookId +
                ", systemUserId=" + systemUserId +
                '}';
    }
}
