package com.example.demogg.model;

import com.google.api.client.util.DateTime;

public class GoogleDrive {
    private String id;
    private String name;
    private DateTime modifiedTime;
    private long size;
    private DateTime createdTime;
    private Boolean starred;

    public GoogleDrive(String name, long size, DateTime createdTime) {
        this.name = name;
        this.size = size;
        this.createdTime = createdTime;
    }

    public GoogleDrive() {
    }

    public DateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(DateTime createdTime) {
        this.createdTime = createdTime;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(DateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
