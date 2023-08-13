package com.training.spotiClone.repositors.jdbc;

import com.training.spotiClone.dao.Song;
import com.training.spotiClone.repositors.ISongRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSongRepository extends JdbcRepository implements ISongRepository {
    @Override
    public Optional<Song> getSongById(long id) {
        String sql = "SELECT * FROM SONG WHERE ID = ?";
        Song song = getJdbcOperations().queryForObject(sql, JdbcSongRepository::mapToSong, id);
        return Optional.ofNullable(song);
    }

    @Override
    public Optional<Song> getRandomSong() {
        return Optional.empty();
    }

    @Override
    public void add(Song song) {
        String sql = "INSERT INTO SONG (ID, NAME, AUTHOR) VALUES ( ?, ?, ?)";
        getJdbcOperations().update(sql, song.getId(), song.getName(), song.getAuthor());
    }

    @Override
    public boolean exists(String name, String author) {
        String sql = "SELECT * FROM SONG WHERE NAME = ? AND AUTHOR = ?";
        List<Song> songs = getJdbcOperations().query(sql, JdbcSongRepository::mapToSong, name, author);
        return !songs.isEmpty();
    }

    protected static Song mapToSong(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String author = rs.getString("author");

        return new Song(id, name, author);
    }
}
