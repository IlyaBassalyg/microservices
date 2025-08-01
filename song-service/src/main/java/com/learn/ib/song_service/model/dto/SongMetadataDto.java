package com.learn.ib.song_service.model.dto;

import com.learn.ib.song_service.validation.annotation.TimeValidation;
import com.learn.ib.song_service.validation.annotation.YearValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SongMetadataDto {
    @Positive
    @NotNull
    private int id;

    @NotNull(message = "Song name is required")
    @Size(min = 1, max = 100)
    private String name;

    @NotNull(message = "Song artist is required")
    @Size(min = 1, max = 100)
    private String artist;

    @NotNull(message = "Song album is required")
    @Size(min = 1, max = 100)
    private String album;

    @NotNull
    @TimeValidation(message = "Duration must be in mm:ss format with leading zeros")
    private String duration;

    @NotNull
    @YearValidation
    private String year;

    public SongMetadataDto(int id, String name, String artist, String album, String duration, String year) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
