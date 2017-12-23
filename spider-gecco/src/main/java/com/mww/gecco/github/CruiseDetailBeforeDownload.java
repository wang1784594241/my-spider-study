package com.mww.gecco.github;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.BeforeDownload;
import com.geccocrawler.gecco.request.HttpRequest;
import com.mww.utils.IpUtil;

@GeccoClass(MyGithub.class)
public class CruiseDetailBeforeDownload implements BeforeDownload {
    @Override
    public void process(HttpRequest request) {

        String ip = IpUtil.randomIp();
        request.addHeader("X-Forwarded-For", ip);
        request.addHeader("X-Forwarded", ip);
        request.addHeader("Client-IP", ip);
        request.addHeader("X-Real-IP", ip);
        request.addHeader("Cluster-Client-IP", ip);
    }
}