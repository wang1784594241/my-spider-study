package com.mww.gecco.github;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl = "https://github.com/{user}/{project}", pipelines = "myGitHubPine")
public class MyGithub implements HtmlBean {

    private static final long serialVersionUID = -7127412585200687225L;

    @RequestParameter("user")
    private String user;//url中的{user}值

    @RequestParameter("project")
    private String project;//url中的{project}值

    @Text
    @HtmlField(cssPath = ".repository-meta-content span")
    private String title;//抽取页面中的title

    @HtmlField(cssPath = ".pagehead-actions li:nth-child(1) .social-count")
    private String watch;//watch

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(2) .social-count")
    private String star;//抽取页面中的star

    @Text
    @HtmlField(cssPath = ".pagehead-actions li:nth-child(3) .social-count")
    private String fork;//抽取页面中的fork

    @Html
    @HtmlField(cssPath = ".entry-content")
    private String readme;//抽取页面中的readme

    public String getReadme() {
        return readme;
    }

    public void setReadme(String readme) {
        this.readme = readme;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getFork() {
        return fork;
    }

    public void setFork(String fork) {
        this.fork = fork;
    }

    @Override
    public String toString() {
        return "MyGithub{" +
                "user='" + user + '\'' +
                ", project='" + project + '\'' +
                ", title='" + title + '\'' +
                ", watch='" + watch + '\'' +
                ", star='" + star + '\'' +
                ", fork='" + fork + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception{
        GeccoEngine.create()
                //工程的包路径
                .classpath("com.mww.gecco")
                //开始抓取的页面地址
                .start("https://github.com/liyiorg/weixin-popular")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                //循环抓取
                .loop(false)
                //使用pc端userAgent
                .debug(false)
                //非阻塞方式运行
                .start();
    }

}