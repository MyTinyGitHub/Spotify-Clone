package com.training.spotiClone.repositors.jdbc;

import com.training.spotiClone.dao.Playlist;
import com.training.spotiClone.dao.User;
import com.training.spotiClone.repositors.IPlaylistRepository;

import java.util.List;
import java.util.Optional;

public class JdbcPlayListRepository implements IPlaylistRepository {
    @Override
    public Optional<Playlist> getPlaylistById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Playlist> getUsersPlayLists(Long userId) {
        return null;
    }

    @Override
    public boolean addPlaylist(Playlist playlist) {
        return false;
    }

    @Override
    public boolean addSongToPlayList(long playListId, long song) {
        return false;
    }
}
