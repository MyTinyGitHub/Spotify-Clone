package com.training.spotiClone.controllers;

import com.training.spotiClone.dao.Song;
import com.training.spotiClone.repositors.list.ListSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongController extends BaseController {

    @GetMapping("/songs")
    public String getAllSongs() {
        return getSongRepository().toString();
    }

    @PostMapping("/songs/add")
    public String addSong(@RequestBody Song song) {
        getSongRepository().add(song);
        return "Pesnicka %s a bola pridana do Databazy pesniciek.".formatted(song.getName());
    }
}
