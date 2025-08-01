package com.learn.ib.song_service.exception;

public class InvalidIDsCSVException extends RuntimeException {
    public InvalidIDsCSVException(String message) {
        super(message);
    }
}
