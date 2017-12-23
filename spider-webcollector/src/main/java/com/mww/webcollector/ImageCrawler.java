package com.mww.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import java.util.ArrayList;
import java.util.List;

public class ImageCrawler extends BreadthCrawler {
    /**
     * @param crawlPath crawlPath is the path of the directory which maintains
     * information of this crawler
     * @param autoParse if autoParse is true,BreadthCrawler will auto extract
     * links which match regex rules from pag
     */
    List<String> urls = new ArrayList<>();

    public ImageCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();
        //如何为每个页面设置代理
        if (page.matchType("list") || page.matchType("next")) {

            /*if type is "list"*/
            /*detect content page by css selector and mark their types as "content"*/
            next.add(page.links("#index_main > div[class='news clearfix'] > div.content > h3 > a")).type("content");
            Links links = page.links("#index_main > div.pagination > a.next_page");
            if (!links.isEmpty()) {
                next.add(new CrawlDatum(links.get(0), "next"));
            }
        } else if (page.matchType("content")) {

        }

    }

    @Override
    public Page getResponse(CrawlDatum crawlDatum) throws Exception {
        HttpRequest request = new HttpRequest(crawlDatum);
        String ip = IpUtil.randomIp();
        request.setHeader("X-Forwarded-For", ip);
        request.setHeader("X-Forwarded", ip);
        request.setHeader("Client-IP", ip);
        request.setHeader("X-Real-IP", ip);
        request.setHeader("Cluster-Client-IP", ip);
        return request.responsePage();
    }

    public static void main(String[] args) throws Exception {
        ImageCrawler crawler = new ImageCrawler("crawl", false);
        crawler.addSeed("http://www.iteye.com/news", "list");
        crawler.setThreads(30);
        crawler.start(5);
    }

}