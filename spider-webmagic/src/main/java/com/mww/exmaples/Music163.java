package com.mww.exmaples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by Administrator on 2017/12/25.
 */
@TargetUrl("http://music.163.com/discover/toplist")
public class Music163 {

    @ExtractBy(value = "//[@id='song-list-pre-cache']/textarea/text()")
    private String song;

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public static void main(String[] args) {
        OOSpider.create(Site.me(),new ConsolePageModelPipeline(), Music163.class)
                .addUrl("http://music.163.com/discover/toplist")
                .run();
    }
}
