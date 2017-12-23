package com.mww.gecco.news;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.mww.gecco.Limit;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */
@Gecco(matchUrl = "http://news.hfut.edu.cn/list-1-{pn}.html", pipelines = "newsPipeline")
public class News implements HtmlBean{

    @RequestParameter("pn")
    private Integer pn;

    @Href(click = true)
    @HtmlField(cssPath = "div[class=' col-lg-8 '] li>a")
    @Limit(isLast = true,last = 2)
    @FieldRenderName("limitRender")
    private List<String> newsList;

    @Href(click = true)
//    @HtmlField(cssPath = "#pages a:last-child")
    @HtmlField(cssPath = "#pages a:contains(下一页)")
    private String nextPage;

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        this.pn = pn;
    }

    public List<String> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<String> newsList) {
        this.newsList = newsList;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public static void main(String[] args) {
        GeccoEngine.create("com.mww.gecco")
                //开始抓取的页面地址
                .start("http://news.hfut.edu.cn/list-1-1.html")
                //开启几个爬虫线程
                .thread(5)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(500)
                //循环抓取
                .loop(false)
                //使用pc端userAgent
                .debug(false)
                //非阻塞方式运行
                .start();
    }
}
