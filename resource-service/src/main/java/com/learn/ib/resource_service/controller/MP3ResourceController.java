package com.learn.ib.resource_service.controller;

import com.learn.ib.resource_service.model.dto.MP3ResourceDto;
import com.learn.ib.resource_service.model.dto.IdDto;
import com.learn.ib.resource_service.model.dto.IdCsvDto;
import com.learn.ib.resource_service.service.MP3ResourceService;
import com.learn.ib.resource_service.validation.annotation.IdCsvValidation;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("resources")
@Validated
public class MP3ResourceController {

    @Autowired
    private MP3ResourceService service;

    @PostMapping
    public ResponseEntity<IdDto> createMP3Resource(@RequestBody byte[] body) throws IOException {
        MP3ResourceDto mp3ResourceDto = new MP3ResourceDto(null, body);
        return ResponseEntity.ok(new IdDto(service.createMP3Resource(mp3ResourceDto)));
    }

    @GetMapping(value = "/{id}", produces = "audio/mpeg")
    public ResponseEntity<byte[]> downloadMP3Resource(@PathVariable("id")
                                                          @Positive(message = "Invalid value '${validatedValue}' for ID. Must be a positive integer")
                                                          int id) {
        return ResponseEntity.ok(service.findMP3Resource(id).body());
    }

    @DeleteMapping
    public ResponseEntity<IdCsvDto> deleteMP3Resources(@RequestParam("id") @IdCsvValidation String id) {
        return ResponseEntity.ok(new IdCsvDto(service.deleteMP3Resources(id)));
    }
}
