package com.ykjzone.pojo;

public class Category {
    public static final String[][] CATEGORY_NAME = {
        {"xuanhuan","玄幻"},
        {"qihuan","奇幻"},
        {"wuxia","武侠"},
        {"xianxia","仙侠"},
        {"dushi","都市"},
        {"xianshi","现实"},
        {"junshi","军事"},
        {"lishi","历史"},
        {"youxi","游戏"},
        {"tiyu","体育"},
        {"kehuan","科幻"},
        {"xuanyi","悬疑"}
    };

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}