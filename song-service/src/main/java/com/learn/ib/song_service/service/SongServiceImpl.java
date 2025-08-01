package com.learn.ib.song_service.service;

import com.learn.ib.song_service.exception.SongAlreadyExists;
import com.learn.ib.song_service.model.dto.SongMetadataDto;
import com.learn.ib.song_service.model.entity.SongMetadata;
import com.learn.ib.song_service.repository.SongRepository;
import com.learn.ib.song_service.service.mapper.SongMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository repository;

    @Autowired
    private SongMapper songMapper;

    @Override
    public Integer createSong(SongMetadataDto songMetadataDto) {
        if (repository.findById(songMetadataDto.getId()).isPresent()) {
            throw new SongAlreadyExists("Metadata for resource ID=" + songMetadataDto.getId() + " already exists");
        }
        return repository.save(songMapper.mapperDtoToEntity().map(songMetadataDto)).getId();
    }

    @Override
    public SongMetadataDto findSong(int id) {
        return songMapper.mapperEntityToDto().map(repository.findById(id).orElseThrow(() -> new NoSuchElementException("Song metadata for ID=" + id + " not found")));
    }

    @Override
    public List<Integer> deleteSongs(String idCsv) {
        List<Integer> ids = Arrays.stream(idCsv.split(",")).map(Integer::parseInt).toList();
        List<Integer> foundResourcesIds = repository.findAllById(ids).stream().map(SongMetadata::getId).toList();
        repository.deleteAllById(foundResourcesIds);
        return foundResourcesIds;
    }
}
