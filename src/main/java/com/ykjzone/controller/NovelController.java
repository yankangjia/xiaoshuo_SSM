package com.ykjzone.controller;

import com.github.pagehelper.PageHelper;
import com.ykjzone.pojo.*;
import com.ykjzone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/novel")
public class NovelController {
    @Autowired
    BannerService bannerService;
    @Autowired
    AdvertisementService advertisementService;
    @Autowired
    NovelService novelService;
    @Autowired
    ChapterService chapterService;
    @Autowired
    ExcellentworksService excellentworksService;

    @RequestMapping("")
    public String index(Model model){
        String[][] all_category_name = Category.CATEGORY_NAME;

        List<Banner> banners = bannerService.getBanners();
        List<Advertisement> ads = advertisementService.getAdvertisements();

        PageHelper.offsetPage(0,6);
        List<Novel> rec_novels_1_6 = novelService.getRecommendNovels(true);

        PageHelper.offsetPage(6,11);
        List<Novel> rec_novels_7_17 = novelService.getRecommendNovels(false);

        PageHelper.offsetPage(0,11);
        List<Novel> new_novels_1_11 = novelService.getNewNovels();

        List<List<Novel>>  cate_hot_novels = novelService.getCateHotNovels();

        List<Excellentworks> excellent_workses = excellentworksService.getExcellentworkses();

        PageHelper.offsetPage(0,11);
        List<Novel> rank_novels = novelService.getRankNovels();

        model.addAttribute("all_category_name",all_category_name);
        model.addAttribute("banners",banners);
        model.addAttribute("ads",ads);
        model.addAttribute("excellent_workses",excellent_workses);
        model.addAttribute("rec_novels_1_6",rec_novels_1_6);
        model.addAttribute("rec_novels_7_17",rec_novels_7_17);
        model.addAttribute("new_novels_1_11",new_novels_1_11);
        model.addAttribute("cate_hot_novels",cate_hot_novels);
        model.addAttribute("rank_novels",rank_novels);
        return "novel/index";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable String id){
        Novel novel = novelService.getByPrimaryKey(id);
        Boolean is_collect = false;
        List<Chapter> chapters = chapterService.getChaptersByNovelId(id);
        PageHelper.offsetPage(0,12);
        List<Novel> recommend_novels = novelService.getRecommendNovels(novel.getCategory().getId(),false);
        String[][] all_category_name = Category.CATEGORY_NAME;

        model.addAttribute("all_category_name",all_category_name);
        model.addAttribute("novel", novel);
        model.addAttribute("chapters", chapters);
        model.addAttribute("is_collect",is_collect);
        model.addAttribute("recent_title",chapters.get(0).getTitle());
        model.addAttribute("recent_date",chapters.get(0).getPub_date());
        model.addAttribute("recommend_novels",recommend_novels);
        return "novel/detail";
    }

    @RequestMapping("/chapter/{id}")
    public String chapter(Model model, @PathVariable String id){
        Chapter chapter = chapterService.getChapterById(id);
        String[][] all_category_name = Category.CATEGORY_NAME;

        // 翻页
        if(chapter.getNumber() > 1){
            int previous_num = chapter.getNumber() - 1;
            String previous_id = chapterService.getIdByNovelAndNum(chapter.getNovel(),previous_num);
            model.addAttribute("previous_id",previous_id);
        }
        if(chapter.getNumber() < chapter.getNovel().getChapters_num()){
            int next_num = chapter.getNumber() + 1;
            String next_id = chapterService.getIdByNovelAndNum(chapter.getNovel(),next_num);
            model.addAttribute("next_id",next_id);
        }

        model.addAttribute("chapter",chapter);
        model.addAttribute("all_category_name",all_category_name);
        return "novel/chapter";
    }
}
