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
        songs.add(new Song("Metalica", "Metalica"));
        songs.add(new Song("Metalica2", "Metalica"));
        songs.add(new Song("Metalica3", "Metalica"));
        songs.add(new Song("Metalica4", "Metalica"));

        songs.add(new Song("HU1", "Hollywood Undead"));
        songs.add(new Song("HU1", "Hollywood Undead"));
        songs.add(new Song("HU1", "Hollywood Undead"));
    }

    public Optional<Song> getSongById(long id) {
        return this.songs.stream().filter( e -> e.getId() == id).findFirst();
    }

    @Override
    public Optional<Song> getRandomSong() {
        int randomPosition = new Random().nextInt(songs.size());
        return Optional.ofNullable(songs.get(randomPosition));
    }

    @Override
    public boolean exists(String name, String author) {
        return songs.stream()
                .anyMatch(song -> song.getName().equals(name) && song.getAuthor().equals(author));
    }

    public void add(Song song) {
        this.songs.add(song);
    }

    public String toString() {
        return songs.toString();
    }

}
