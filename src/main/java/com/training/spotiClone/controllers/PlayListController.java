package com.training.spotiClone.controllers;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.training.spotiClone.dao.Playlist;
import com.training.spotiClone.dao.Song;
import com.training.spotiClone.dao.User;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class PlayListController extends BaseController {
    @GetMapping("/playlist")
    public String getAllSongs(@RequestBody Map<String, Long> input) {
        return getPlaylistRepository().getUsersPlayLists(input.getOrDefault("userId", -1L)).toString();
    }

    @PostMapping("/playlist/add")
    public String addPlaylist(@RequestBody Map<String, String> input) {
        Long userId = Long.valueOf(input.get("ownerId"));

        String name = input.get("name");
        if(name == null) {
            return "No name of playlist provided";
        }

        boolean songAdded = getPlaylistRepository().addPlaylist(new Playlist(userId, name));
        return songAdded ? "Playlist successfully added" : "Not possible to add playlist";
    }

    @PostMapping("playlist/addSong")
    public String addSongToPlaylist(@RequestBody Map<String, Long> input) {
        long playlistId = input.getOrDefault("playlistId", -1L);
        long songId = input.getOrDefault("songId", -1L);

        return getPlaylistRepository().addSongToPlayList(playlistId, songId) ?
                "Song was added to playlist" : "It was not possible to add the song to playlist";
    }
}
