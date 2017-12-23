package com.mww.gecco.news;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * Created by Administrator on 2017/12/22.
 */
@PipelineName("newsPipeline")
public class NewsPipeline implements Pipeline<News> {

    @Override
    public void process(News bean) {
        System.out.println(bean.getPn());
        System.out.println(bean.getNewsList());
        System.out.println(bean.getNextPage());
    }
}
