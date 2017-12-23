package com.mww.gecco.github;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * Created by Administrator on 2017/12/21.
 */
@PipelineName("myGitHubPine")
public class MyGitHubPine implements Pipeline<MyGithub> {

    @Override
    public void process(MyGithub myGithub) {
        System.out.println(myGithub);

    }
}
