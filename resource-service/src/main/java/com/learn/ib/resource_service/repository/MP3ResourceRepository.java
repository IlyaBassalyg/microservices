package com.learn.ib.resource_service.repository;

import com.learn.ib.resource_service.model.entity.MP3Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MP3ResourceRepository extends JpaRepository<MP3Resource, Integer> {
}
