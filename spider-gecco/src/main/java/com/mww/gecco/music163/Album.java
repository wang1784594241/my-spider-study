package com.mww.gecco.music163;

/**
 * Created by Administrator on 2017/12/21.
 */
public class Album {

    private Long id;
    //歌名
    private String name;
    //图片
    private String picUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

}
