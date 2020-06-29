package com.ykjzone.pojo;

public class UserCollect {
    private Integer id;

    // user_id
    private User user;

    // novel_id
    private Novel novel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Novel getNovel() {
        return novel;
    }

    public void setNovel(Novel novel) {
        this.novel = novel;
    }
}