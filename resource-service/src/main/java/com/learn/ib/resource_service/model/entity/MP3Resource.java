package com.learn.ib.resource_service.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "mp3_resource")
@SequenceGenerator(
        name = "mp3_resource_seq",
        sequenceName = "mp3_resource_id_seq",
        allocationSize = 1
)
public class MP3Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mp3_resource_seq")
    private Integer id;
    private byte[] body;

    public MP3Resource() {
    }

    public MP3Resource(byte[] body) {
        this.body = body;
    }

    public MP3Resource(Integer id, byte[] body) {
        this.id = id;
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
