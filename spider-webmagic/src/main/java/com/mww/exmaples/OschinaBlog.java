package com.mww.exmaples;

import com.mww.downloader.MyDownloader;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.util.List;

//@TargetUrl("http://my.oschina.net/flashsword/blog/\\d+")
@TargetUrl("https://my.oschina.net/flashsword/blog/1541177")
public class OschinaBlog {

    @ExtractBy("//title/text()")
    private String title;

//    @ExtractBy(value = "div.BlogContent",type = ExtractBy.Type.Css)
    @ExtractBy(value = "//div[@class='BlogContent']/p/text()| //div[@class='BlogContent']/p/a/text()")
    private List<String> content;

    @ExtractBy(value = "//div[@class='BlogTags']/a/text()", multi = true)
    private List<String> tags;

    public static void main(String[] args) {
        OOSpider.create(
                Site.me(),
			new ConsolePageModelPipeline(), OschinaBlog.class).setDownloader(new MyDownloader(5)).addUrl("https://my" +
                ".oschina" +
                ".net/flashsword/blog/1541177").run();
    }
}