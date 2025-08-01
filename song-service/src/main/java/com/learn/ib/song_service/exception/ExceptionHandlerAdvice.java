package com.learn.ib.song_service.exception;

import com.learn.ib.song_service.model.ErrorResponseDto;
import com.learn.ib.song_service.model.dto.MetadataValidationDetailsDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "";
        if (("id").equals(ex.getPropertyName())) {
            errorMessage = "Invalid value '" + ex.getValue() + "' for ID. Must be a positive integer";
        }
        else {
            errorMessage = ex.getMessage();
        }
        return new ResponseEntity<>(new ErrorResponseDto(errorMessage, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponseDto> handleNoSuchElement(NoSuchElementException e) {
        return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {

        MetadataValidationDetailsDto details = new MetadataValidationDetailsDto();
        ex.getAllErrors().stream()
                .filter(error -> error instanceof FieldError)
                .forEach(error -> {
                    if (error.getObjectName().equals("songMetadataDto")) {
                        switch (((FieldError) error).getField()) {
                            case "name" -> details.setName(error.getDefaultMessage());
                            case "artist" -> details.setArtist(error.getDefaultMessage());
                            case "album" -> details.setAlbum(error.getDefaultMessage());
                            case "duration" -> details.setDuration(error.getDefaultMessage());
                            case "year" -> details.setYear(error.getDefaultMessage());
                        }
                    }
                });
        return new ResponseEntity<>(new ErrorResponseDto("Validation Error", HttpStatus.BAD_REQUEST.value(), details), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException e) {
        String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).filter(message -> !message.isEmpty()).findFirst().orElse(e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDto(errorMessage, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SongAlreadyExists.class)
    public ResponseEntity<ErrorResponseDto> SongAlreadyExistsException(SongAlreadyExists e) {
        return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({InvalidIDsCSVException.class, InvalidBodyException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequest (RuntimeException e) {
        return new ResponseEntity<>(new ErrorResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGeneralException() {
        return new ResponseEntity<>(new ErrorResponseDto("An error occurred on the server.", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
