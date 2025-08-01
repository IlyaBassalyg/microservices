package com.learn.ib.song_service.service;

import com.learn.ib.song_service.model.dto.SongMetadataDto;

import java.util.List;

public interface SongService {
    Integer createSong(SongMetadataDto songMetadataDto);
    SongMetadataDto findSong(int id);
    List<Integer> deleteSongs(String idCsv);
}
