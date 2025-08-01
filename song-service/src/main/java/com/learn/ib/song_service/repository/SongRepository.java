package com.learn.ib.song_service.repository;

import com.learn.ib.song_service.model.entity.SongMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongMetadata, Integer> {
}
