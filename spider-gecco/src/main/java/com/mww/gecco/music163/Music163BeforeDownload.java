package com.mww.gecco.music163;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.BeforeDownload;
import com.geccocrawler.gecco.request.HttpRequest;

/**
 * Created by Administrator on 2017/12/21.
 */
@GeccoClass(Music163.class)
public class Music163BeforeDownload implements BeforeDownload {

    @Override
    public void process(HttpRequest request) {
    }
}
