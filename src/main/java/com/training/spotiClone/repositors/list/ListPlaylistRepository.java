package com.training.spotiClone.repositors.list;

import com.training.spotiClone.dao.Playlist;
import com.training.spotiClone.dao.Song;
import com.training.spotiClone.dao.User;
import com.training.spotiClone.dao.UserType;
import com.training.spotiClone.repositors.IPlaylistRepository;
import com.training.spotiClone.repositors.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ListPlaylistRepository implements IPlaylistRepository {
    private final HashMap<Long, List<Playlist>> playListTable = new HashMap<>();
    private static final int PLAYLIST_LIMIT = 5;

    @Autowired
    private ISongRepository songRepository;
    @Autowired
    private ListUserRepository userRepository;

    @Override
    public Optional<Playlist> getPlaylistById(long id) {
        return playListTable.values().stream()
                .flatMap(Collection::stream)
                .filter( playlist -> playlist.getId() == id)
                .findFirst();
    }

    @Override
    public List<Playlist> getUsersPlayLists(Long userId) {
        return playListTable.getOrDefault(userId, List.of());
    }
    @Override
    public boolean addPlaylist(Playlist playlist) {
        User user = userRepository.getUserById(playlist.getOwnerId());

        List<Playlist> playlists = playListTable.getOrDefault(user.getId(), List.of());

        boolean isGuest = UserType.GUEST.equals(user.getType());
        boolean isPremium = UserType.PREMIUM.equals(user.getType());
        boolean isAllowed = isPremium || playlists.size() < PLAYLIST_LIMIT;

        if(isGuest) {
            return false;
        }

        if(!isAllowed) {
            return false;
        }

        playListTable.merge(user.getId(), List.of(playlist), this::combine);
        return true;
    }

    @Override
    public boolean addSongToPlayList(long playListId, long songId) {
        Optional<Playlist> playList = getPlaylistById(playListId);
        if(playList.isEmpty()) return false;

        Optional<Song> song = songRepository.getSongById(songId);
        if(song.isEmpty()) return false;

        playList.get().getSongs().add(song.get());

        return true;
    }

    private List<Playlist> combine(List<Playlist> l1, List<Playlist> l2) {
        l1.addAll(l2);
        return l1;
    }

    public String toString() {
        return playListTable.entrySet().stream()
                .map( entry -> entry.getKey().toString() + entry.getValue().toString())
                .collect(Collectors.joining());
    }
}
