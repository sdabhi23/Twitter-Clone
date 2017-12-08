package com.shrey.task1sample;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created by Shrey on 12/3/2017.
 */

public class Tweet {

    private String name, handle, minutes, content;
    private int prof, conImg;
    private int comment, rt, like;

    public Tweet() {

    }

    public Tweet(String name, String handle, String minutes, String content, int prof, int conImg, int comment, int rt, int like) {
        this.name = name;
        this.handle = handle;
        this.minutes = minutes;
        this.content = content;
        this.prof = prof;
        this.conImg = conImg;
        this.comment = comment;
        this.rt = rt;
        this.like = like;
    }

    public Tweet(String name, String handle, String minutes, String content, int prof, int conImg) {
        this.name = name;
        this.handle = handle;
        this.minutes = minutes;
        this.content = content;
        this.prof = prof;
        this.conImg = conImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProf() {
        return prof;
    }

    public void setProf(int prof) {
        this.prof = prof;
    }

    public int getConImg() {
        return conImg;
    }

    public void setConImg(int conImg) {
        this.conImg = conImg;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
