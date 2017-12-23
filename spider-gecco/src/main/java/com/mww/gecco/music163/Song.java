package com.mww.gecco.music163;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */
public class Song {

    private Long id;

    //歌名
    private String name;

    //持续时长
    private Integer duration;

    //专辑
    private Album album;

    //歌手
    private List<Artists> artists;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }
}
