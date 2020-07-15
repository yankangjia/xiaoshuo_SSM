package com.ykjzone.validate;

import com.ykjzone.pojo.Novel;
import com.ykjzone.service.NovelService;

import javax.servlet.http.HttpServletRequest;

public class NovelValidator extends Validator {
    NovelService novelService;

    private String name;
    private String profile;
    private String price;
    private String cover_url;
    private String category_id;
    private String tag_id;

    public NovelValidator(HttpServletRequest request, NovelService novelService){
        super(request);
        this.novelService = novelService;

        this.name = (String) request.getParameter("name");
        this.profile = (String) request.getParameter("profile");
        this.price = (String) request.getParameter("price");
        this.cover_url = (String) request.getParameter("cover_url");
        this.category_id = (String) request.getParameter("category_id");
        this.tag_id = (String) request.getParameter("tag_id");
    }

    @Override
    public boolean clean() {
        return cleanName() && cleanProfile() && cleanPrice() && cleanCover_url() && cleanCategory_id() && cleanTag_id();
    }

    public boolean cleanName(){
        if(name == null || name.equals("")){
            message = "小说名称不能为空";
            return false;
        }
        if(name.length() > 200){
            message = "小说名称长度不能超过200";
            return false;
        }
        return true;
    }

    public boolean cleanProfile(){
        if(profile == null || profile.equals("")){
            message = "小说描述不能为空";
            return false;
        }
        return true;
    }

    public boolean cleanPrice(){
        if(price == null || price.equals("")){
            message = "价格不能为空";
            return false;
        }
        if(price.matches("^\\d+(\\.\\d+)?$"))
            return true;
        return false;
    }

    public boolean cleanCategory_id(){
        if(category_id == null || category_id.equals("")){
            message = "分类不能为空";
            return false;
        }
        if(price.matches("^\\d+$"))
            return true;
        return false;
    }

    public boolean cleanCover_url(){
        if(cover_url == null || cover_url.equals("")){
            message = "封面图片不能为空";
            return false;
        }
        if(cover_url.matches("^(\\w+://)?[\\w\\d]+(\\.[\\w\\d]+)+$"))
            return true;
        return false;
    }

    public boolean cleanTag_id(){
        if(tag_id == null || tag_id.equals("")){
            message = "标签不能为空";
            return false;
        }
        if(price.matches("^\\d+$"))
            return true;
        return false;
    }

    @Override
    public Object save() {
        Novel novel = new Novel();
        novel.setName(name);
        novel.setProfile(profile);
        novel.setPrice(Double.parseDouble(price));
        novel.setCover_url(cover_url);
//        novel.setCategory();
        return null;
    }

    public static void main(String[] args){
        if("132.3".matches("^\\d+$")){
            System.out.println("true");
        }
    }
}
