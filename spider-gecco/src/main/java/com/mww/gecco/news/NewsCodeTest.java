package com.mww.gecco.news;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * Created by Administrator on 2017/12/22.
 */
public class NewsCodeTest {
    public static void main(String[] args) {
        DynamicGecco.html()
                .gecco("http://news.hfut.edu.cn/list-1-{pn}.html", "consolePipeline")
                .stringField("nextPage").csspath("#pages a:contains(下一页)").href(true).build()
                .listField("newsList",
                        DynamicGecco.html()
                                .stringField("url")
                                .csspath("a")
                                .href(false)
                                .build()
                                .register())
                .csspath("div[class=' col-lg-8 '] li>a:lt(3)").build().register();

//开始抓取
        GeccoEngine.create()
                .classpath("gecco.news")
                .debug(false)
                .start("http://news.hfut.edu.cn/list-1-1.html")
                .run();
    }
}
