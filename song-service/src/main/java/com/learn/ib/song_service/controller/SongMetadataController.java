package com.learn.ib.song_service.controller;

import com.learn.ib.song_service.model.dto.IdCsvDto;
import com.learn.ib.song_service.model.dto.IdDto;
import com.learn.ib.song_service.model.dto.SongMetadataDto;
import com.learn.ib.song_service.service.SongService;
import com.learn.ib.song_service.validation.annotation.IdCsvValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@Validated
public class SongMetadataController {

    @Autowired
    private SongService service;

    @PostMapping
    ResponseEntity<IdDto> createSongMetadata(@RequestBody @Valid SongMetadataDto songMetadataDto) {
        return ResponseEntity.ok(new IdDto(service.createSong(songMetadataDto)));
    }

    @GetMapping("/{id}")
    ResponseEntity<SongMetadataDto> findSongMetadata(@PathVariable("id")
                                                     @Positive(message = "Invalid value '${validatedValue}' for ID. Must be a positive integer")
                                                     Integer id) {
        return ResponseEntity.ok(service.findSong(id));
    }

    @DeleteMapping
    ResponseEntity<IdCsvDto> deleteSongsMetadata(@RequestParam("id") @IdCsvValidation String id) {
        return ResponseEntity.ok(new IdCsvDto(service.deleteSongs(id)));
    }
}
