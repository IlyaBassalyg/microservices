package com.learn.ib.resource_service.service.mapper;

import com.learn.ib.resource_service.model.dto.MP3ResourceDto;
import com.learn.ib.resource_service.model.entity.MP3Resource;
import org.springframework.stereotype.Service;

@Service
public class MP3ResourceMapper {
    private final Mapper<MP3Resource, MP3ResourceDto> entityToDto =
            entity -> new MP3ResourceDto(entity.getId(), entity.getBody());

    private final Mapper<MP3ResourceDto, MP3Resource> dtoToEntity =
            dto -> new MP3Resource(dto.body());

    public Mapper<MP3Resource, MP3ResourceDto> mapperEntityToDto() {
        return entityToDto;
    }

    public Mapper<MP3ResourceDto, MP3Resource> mapperDtoToEntity() {
        return dtoToEntity;
    }
}
