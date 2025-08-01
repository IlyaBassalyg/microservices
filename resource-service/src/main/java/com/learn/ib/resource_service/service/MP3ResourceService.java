package com.learn.ib.resource_service.service;

import com.learn.ib.resource_service.model.dto.MP3ResourceDto;

import java.util.List;

public interface MP3ResourceService {
    Integer createMP3Resource(MP3ResourceDto mp3ResourceDto);
    MP3ResourceDto findMP3Resource(int id);
    List<Integer> deleteMP3Resources(String id);
}
