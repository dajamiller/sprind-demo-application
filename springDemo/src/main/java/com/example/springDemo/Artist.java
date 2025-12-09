package com.example.springDemo;

//model
public class Artist {

    //variables
    private String name;
    private String albumTitle;
    private String songTitle;

    // constructor
    public Artist(String name, String albumTitle, String songTitle) {
        this.name = name;
        this.albumTitle = albumTitle;
        this.songTitle = songTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
