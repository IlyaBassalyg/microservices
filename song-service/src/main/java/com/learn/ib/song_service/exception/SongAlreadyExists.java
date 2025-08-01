package com.learn.ib.song_service.exception;

public class SongAlreadyExists extends RuntimeException {
    public SongAlreadyExists(String message) {
        super(message);
    }
}
