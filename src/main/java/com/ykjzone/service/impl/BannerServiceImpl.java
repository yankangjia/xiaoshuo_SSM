package com.ykjzone.service.impl;

import com.ykjzone.mapper.BannerMapper;
import com.ykjzone.pojo.Banner;
import com.ykjzone.pojo.BannerExample;
import com.ykjzone.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    public List<Banner> getBanners() {
        BannerExample example = new BannerExample();
        List<Banner> banners = bannerMapper.selectByExample(example);
        return banners;
    }
}
