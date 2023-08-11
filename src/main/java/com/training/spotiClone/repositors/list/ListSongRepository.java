package com.training.spotiClone.repositors.list;

import com.training.spotiClone.dao.Song;
import com.training.spotiClone.repositors.ISongRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public class ListSongRepository implements ISongRepository {
    private List<Song> songs = new ArrayList<>();
    {
        songs.add(new Song(1, "Metalica", "Metalica"));
        songs.add(new Song(2, "Metalica2", "Metalica"));
        songs.add(new Song(3, "Metalica3", "Metalica"));
        songs.add(new Song(4, "Metalica4", "Metalica"));

        songs.add(new Song(5, "HU1", "Holywood Undead"));
        songs.add(new Song(6, "HU1", "Holywood Undead"));
        songs.add(new Song(7, "HU1", "Holywood Undead"));
    }

    public Optional<Song> getSongById(long id) {
        return this.songs.stream().filter( e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Song> getRandomSong() {
        int randomPosition = new Random().nextInt(songs.size());
        return Optional.ofNullable(songs.get(randomPosition));
    }

    public void add(Song song) {
        this.songs.add(song);
    }

    public String toString() {
        return songs.toString();
    }

}
