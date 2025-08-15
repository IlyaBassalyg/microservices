package com.learn.ib.resource_service.service;

import com.learn.ib.resource_service.exception.InvalidBodyException;
import com.learn.ib.resource_service.model.dto.*;
import com.learn.ib.resource_service.model.entity.MP3Resource;
import com.learn.ib.resource_service.repository.MP3ResourceRepository;
import com.learn.ib.resource_service.restclient.RestConfiguration;
import com.learn.ib.resource_service.service.mapper.MP3ResourceMapper;
import com.learn.ib.resource_service.service.parser.MP3Parser;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MP3ResourceServiceImpl implements MP3ResourceService {

    @Autowired
    private RestConfiguration restConfiguration;

    @Autowired
    private MP3ResourceRepository repository;

    @Autowired
    private MP3ResourceMapper mapper;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestClient restClient;

    @Lookup
    protected MP3Parser getMP3Parser(BodyContentHandler handler, Metadata metadata, ParseContext pcontext, byte[] body) {
        return null;
    };

    @Override
    public Integer createMP3Resource(MP3ResourceDto mp3ResourceDto) {
        Integer createdId = repository.save(mapper.mapperDtoToEntity().map(mp3ResourceDto)).getId();
        SongMetadataDto songMetadataDto = getMP3Parser(new BodyContentHandler(), new Metadata(), new ParseContext(), mp3ResourceDto.body())
                .parseBody(createdId);
        ServiceInstance serviceInstance = discoveryClient.getInstances("song-service").get(0);
        IdDto body = restClient.post().uri(serviceInstance.getUri() + "/songs").body(songMetadataDto).retrieve().body(IdDto.class);
        if (body == null && body.id() <= 0) {
            repository.deleteById(createdId);
            throw new InvalidBodyException("Failed to create song metadata for the resource");
        }
        return createdId;
    }

    @Override
    public MP3ResourceDto findMP3Resource(int id) {
        return mapper.mapperEntityToDto()
                .map(repository.findById(id).orElseThrow(() -> new NoSuchElementException("Song metadata for ID=" + id + " not found")));
    }

    @Override
    public List<Integer> deleteMP3Resources(String id) {
        List<Integer> ids = Arrays.stream(id.split(",")).map(Integer::parseInt).toList();
        List<Integer> foundResourcesIds = repository.findAllById(ids).stream().map(MP3Resource::getId).toList();
        ServiceInstance serviceInstance = discoveryClient.getInstances("song-service").get(0);
        IdCsvDto idCsvDto = restClient.delete().uri(serviceInstance.getUri() + "/songs?id={id}", id).retrieve().body(IdCsvDto.class);
        repository.deleteAllById(idCsvDto != null ? idCsvDto.ids() : new ArrayList<>());
        return foundResourcesIds;
    }
}
