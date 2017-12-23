package com.mww.gecco.music163;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/12/21.
 */
@PipelineName("music163pipeline")
public class Music163pipeline implements Pipeline<Music163> {

    @Override
    public void process(Music163 bean) {
        String songs = bean.getSongs();
        System.out.println(songs);
        JSONArray array = JSON.parseArray(songs);
        ArrayList<Song> songList = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            Song song = array.getJSONObject(i).toJavaObject(Song.class);
            songList.add(song);
        }
        System.out.println(songList.size());
    }
}
