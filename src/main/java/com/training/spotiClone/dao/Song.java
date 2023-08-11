package com.training.spotiClone.dao;

import lombok.Data;

@Data
public class Song {
    private long id;
    private String name;
    private String author;

    public Song(long id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
