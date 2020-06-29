package com.ykjzone.pojo;

public class Permission {
    private Integer id;

    private String name;

    // content_type_id
    private ContentType content_type;

    private String codename;

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

    public ContentType getContent_type() {
        return content_type;
    }

    public void setContent_type(ContentType content_type) {
        this.content_type = content_type;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename == null ? null : codename.trim();
    }
}