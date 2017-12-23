package com.mww.gecco.github;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.dynamic.DynamicGecco;

/**
 * Created by Administrator on 2017/12/22.
 */
public class CodeTest {
    public static void main(String[] args) {
        DynamicGecco.html()
                .gecco("https://github.com/{user}/{project}", "consolePipeline")
                .requestField("request").request().build()
                .stringField("user").requestParameter("user").build()
                .stringField("project").requestParameter("project").build()
                .stringField("title").csspath(".repository-meta-content").text(false).build()
                .stringField("star").csspath(".pagehead-actions li:nth-child(2) .social-count").text(false).build()
                .stringField("fork").csspath(".pagehead-actions li:nth-child(3) .social-count").text().build()
                .stringField("contributors").csspath("ul.numbers-summary > li:nth-child(4) > a").href().build()
                .register();

//开始抓取
        GeccoEngine.create()
                .classpath("a")
                .start("https://github.com/xtuhcy/gecco")
                .run();
    }
}
