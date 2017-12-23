package com.mww.gecco.news;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/22.
 */
@Gecco(matchUrl = "http://news.hfut.edu.cn/show-1-{id}-1.html", pipelines = "consolePipeline")
public class Content implements HtmlBean {

    @Text
    @HtmlField(cssPath = "#Article h2")
    private String title;

    @Text
    @HtmlField(cssPath = "#artibody p span,#artibody p")
    private List<String> content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
