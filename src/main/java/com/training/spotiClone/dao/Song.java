package com.training.spotiClone.dao;

import com.training.spotiClone.repositors.list.IdProvider;
import lombok.Data;

@Data
public class Song {
    private long id;
    private String name;
    private String author;

    public Song(String name, String author) {
        this.id = IdProvider.generateSongId();
        this.name = name;
        this.author = author;
    }
}
