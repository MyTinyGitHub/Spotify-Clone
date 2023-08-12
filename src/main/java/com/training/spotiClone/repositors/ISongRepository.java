package com.training.spotiClone.repositors;

import com.training.spotiClone.dao.Song;

import java.util.Optional;

public interface ISongRepository {
    public Optional<Song> getSongById(long id);
    public Optional<Song> getRandomSong();
    public void add(Song song);
    public boolean exists(String name, String author);
}
