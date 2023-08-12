package com.training.spotiClone.repositors;

import com.training.spotiClone.dao.Playlist;
import com.training.spotiClone.dao.Song;
import com.training.spotiClone.dao.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlaylistRepository {
    public Optional<Playlist> getPlaylistById(long id);
    public List<Playlist> getUsersPlayLists(Long userId);
    public boolean addPlaylist(Playlist playlist);
    public boolean addSongToPlayList(long playListId, long song);

    public String toString();
}
