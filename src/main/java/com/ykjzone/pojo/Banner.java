package com.ykjzone.pojo;

import java.util.Date;

public class Banner {
    private Integer id;

    private Integer priority;

    private String image_url;

    private String link_to;

    private Date pub_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url == null ? null : image_url.trim();
    }

    public String getLink_to() {
        return link_to;
    }

    public void setLink_to(String link_to) {
        this.link_to = link_to == null ? null : link_to.trim();
    }

    public Date getPub_time() {
        return pub_time;
    }

    public void setPub_time(Date pub_time) {
        this.pub_time = pub_time;
    }
}