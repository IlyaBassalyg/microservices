package com.learn.ib.song_service.service.mapper;

import com.learn.ib.song_service.model.dto.SongMetadataDto;
import com.learn.ib.song_service.model.entity.SongMetadata;
import org.springframework.stereotype.Service;

@Service
public class SongMapper {
    private final Mapper<SongMetadata, SongMetadataDto> entityToDto =
            entity -> new SongMetadataDto(entity.getId(),
                    entity.getName(),
                    entity.getArtist(),
                    entity.getAlbum(),
                    entity.getDuration(),
                    entity.getYear());

    private final Mapper<SongMetadataDto, SongMetadata> dtoToEntity =
            dto -> new SongMetadata(dto.getId(),
                    dto.getName(),
                    dto.getArtist(),
                    dto.getAlbum(),
                    dto.getDuration(),
                    dto.getYear());

    public Mapper<SongMetadata, SongMetadataDto> mapperEntityToDto() {
        return entityToDto;
    }

    public Mapper<SongMetadataDto, SongMetadata> mapperDtoToEntity() {
        return dtoToEntity;
    }
}
