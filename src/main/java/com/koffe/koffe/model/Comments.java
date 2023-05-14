package com.koffe.koffe.model;

import java.sql.Timestamp;

public class Comments {
    private int commentsId;
    private String commentsFullName;
    private String commentsEmail;
    private String commentsSubject;
    private String commentsMessage;
    private Timestamp commentsTime;

    public Comments() {
    }

    public Comments(String commentsFullName, String commentsEmail, String commentsSubject, String commentsMessage, Timestamp commentsTime) {
        this.commentsFullName = commentsFullName;
        this.commentsEmail = commentsEmail;
        this.commentsSubject = commentsSubject;
        this.commentsMessage = commentsMessage;
        this.commentsTime = commentsTime;
    }

    public Comments(int commentsId, String commentsFullName, String commentsEmail, String commentsSubject, String commentsMessage, Timestamp commentsTime) {
        this.commentsId = commentsId;
        this.commentsFullName = commentsFullName;
        this.commentsEmail = commentsEmail;
        this.commentsSubject = commentsSubject;
        this.commentsMessage = commentsMessage;
        this.commentsTime = commentsTime;
    }

    public String getCommentsEmail() {
        return commentsEmail;
    }

    public void setCommentsEmail(String commentsEmail) {
        this.commentsEmail = commentsEmail;
    }

    public int getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(int commentsId) {
        this.commentsId = commentsId;
    }

    public String getCommentsFullName() {
        return commentsFullName;
    }

    public void setCommentsFullName(String commentsFullName) {
        this.commentsFullName = commentsFullName;
    }

    public String getCommentsSubject() {
        return commentsSubject;
    }

    public void setCommentsSubject(String commentsSubject) {
        this.commentsSubject = commentsSubject;
    }

    public String getCommentsMessage() {
        return commentsMessage;
    }

    public void setCommentsMessage(String commentsMessage) {
        this.commentsMessage = commentsMessage;
    }

    public Timestamp getCommentsTime() {
        return commentsTime;
    }

    public void setCommentsTime(Timestamp commentsTime) {
        this.commentsTime = commentsTime;
    }
}
