package com.learn.ib.resource_service.model.entity;

import jakarta.persistence.*;

@Entity
public class MP3Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
