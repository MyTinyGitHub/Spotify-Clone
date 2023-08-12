package com.training.spotiClone.dao;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class Playlist {
    private long id;
    private long ownerId;
    private String name;
    private List<Song> songs;

    public Playlist(long ownerId, String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.songs = new ArrayList<>();
    }
}
