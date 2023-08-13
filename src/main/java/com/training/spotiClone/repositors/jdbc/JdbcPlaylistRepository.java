package com.training.spotiClone.repositors.jdbc;

import com.training.spotiClone.dao.Playlist;
import com.training.spotiClone.dao.Song;
import com.training.spotiClone.dao.UserType;
import com.training.spotiClone.repositors.IPlaylistRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcPlaylistRepository extends JdbcRepository implements IPlaylistRepository {
    @Override
    public Optional<Playlist> getPlaylistById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Playlist> getUsersPlayLists(Long userId) {
        String sql = "SELECT * FROM PLAYLIST WHERE owner_id = ?";
        List<Playlist> playlists = getJdbcOperations().query(sql, JdbcPlaylistRepository::mapToPlaylist, userId);

        playlists.forEach(playlist -> playlist.setSongs(selectSongsToPlaylist(playlist.getId())));

        return playlists;
    }

    private List<Song> selectSongsToPlaylist(long playlistId) {
        String sql = "SELECT ID, NAME, AUTHOR FROM SONG " +
                "JOIN playlistToSong ON SONG.ID = playlistToSong.song_id " +
                "WHERE playlist_id = ?";

        return getJdbcOperations().query(sql, JdbcSongRepository::mapToSong, playlistId);
    }

    @Override
    public boolean addPlaylist(Playlist playlist) {

        boolean userNotAllowed = playlist.getOwnerId() <= 0;
        if(userNotAllowed) {
            return false;
        }

        String selectSql = "SELECT COUNT(1) AS COUNT, TYPE FROM PLAYLIST " +
                        "JOIN USERS ON OWNER_ID = USERS.ID " +
                        "WHERE OWNER_ID = ?";

        Map<String,Object> result = getJdbcOperations().queryForMap(selectSql, playlist.getOwnerId());

        Boolean isPremiumUser = UserType.valueOf((Integer) result.get("TYPE")).equals(UserType.PREMIUM);
        Long numberOfPlaylists = (Long) result.get("COUNT");

        if(!isPremiumUser && numberOfPlaylists >= PLAYLIST_LIMIT) {
            return false;
        }

        String insertSql = "INSERT INTO PLAYLIST (id, owner_id, name) VALUES (?, ?, ?)";
        return getJdbcOperations().update(insertSql, playlist.getId(), playlist.getOwnerId(), playlist.getName()) > 0;
    }

    @Override
    public boolean addSongToPlayList(long playListId, long songId) {
        String sql = "INSERT INTO PLAYLISTTOSONG (playlist_id, song_id) VALUES (?, ?)";
        return getJdbcOperations().update(sql, playListId, songId) > 0;
    }

    protected static Playlist mapToPlaylist(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Long ownerId = rs.getLong("owner_id");
        String name = rs.getString("name");

        return new Playlist(id, ownerId, name);
    }
}
