package com.training.spotiClone.dao;

import com.training.spotiClone.repositors.list.IdProvider;
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

    public Playlist(long id, long ownerId, String name) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
    }

    public Playlist(long ownerId, String name) {
        this.id = IdProvider.generatePlaylistId();
        this.ownerId = ownerId;
        this.name = name;
        this.songs = new ArrayList<>();
    }
}
