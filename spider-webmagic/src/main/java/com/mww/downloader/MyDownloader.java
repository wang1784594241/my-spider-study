package com.mww.downloader;

import com.mww.utils.IpUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * Created by Administrator on 2017/12/25.
 */
public class MyDownloader implements Downloader {

    private int threadNum;

    private HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

    public MyDownloader() {
    }

    public MyDownloader(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public Page download(Request request, Task task) {
        String ip = IpUtil.randomIp();
        request.addHeader("X-Forwarded-For", ip);
        request.addHeader("X-Forwarded", ip);
        request.addHeader("Client-IP", ip);
        request.addHeader("X-Real-IP", ip);
        request.addHeader("Cluster-Client-IP", ip);
        return httpClientDownloader.download(request, task);
    }

    @Override
    public void setThread(int threadNum) {
        this.threadNum = threadNum;
    }
}
