package com.learn.ib.song_service.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SongMetadata {
    @Id
    private int id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String year;

    public SongMetadata() {
    }

    public SongMetadata(int id, String name, String artist, String album, String duration, String year) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getDuration() {
        return duration;
    }

    public String getYear() {
        return year;
    }
}
