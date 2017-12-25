package com.mww.gecco.music163;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * Created by Administrator on 2017/12/21.
 */
@Gecco(matchUrl = "http://music.163.com/discover/toplist", pipelines = "music163pipeline")
public class Music163 implements HtmlBean{

    @HtmlField(cssPath = "#song-list-pre-cache textarea")
    private String songs;

    public String getSongs() {
        return songs;
    }

    public void setSongs(String songs) {
        this.songs = songs;
    }

    public static void main(String[] args) throws Exception {
        GeccoEngine.create()
                //工程的包路径
                .classpath("com.mww.gecco.music163")
                //开始抓取的页面地址
                .start("http://music.163.com/discover/toplist")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                //循环抓取
                .loop(false)
                .debug(false)
                //使用pc端userAgent
                .mobile(false)
                //非阻塞方式运行
                .start();
    }
}
