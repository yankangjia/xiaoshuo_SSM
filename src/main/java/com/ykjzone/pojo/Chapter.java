package com.ykjzone.pojo;

import java.util.Date;

public class Chapter {
    private String id;

    private String title;

    private Integer number;

    private Integer words_num;

    private Date pub_date;

    private String content;

    // 非数据库字段
    private Novel novel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getWords_num() {
        return words_num;
    }

    public void setWords_num(Integer words_num) {
        this.words_num = words_num;
    }

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }

    public Date getPub_date() {
        return pub_date;
    }

    public void setPub_date(Date pub_date) {
        this.pub_date = pub_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}