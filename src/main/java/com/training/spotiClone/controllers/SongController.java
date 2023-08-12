package com.training.spotiClone.controllers;

import com.training.spotiClone.dao.Song;
import com.training.spotiClone.repositors.list.ListSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SongController extends BaseController {

    @GetMapping("/songs")
    public String getAllSongs() {
        return getSongRepository().toString();
    }

    @PostMapping("/songs/add")
    public String addSong(@RequestParam Map<String, String> input) {
        String name = input.get("name");
        if(name == null) {
            return "No name provided";
        }

        String author = input.get("author");
        if(author == null) {
            return "No author provided";
        }

        boolean exists = getSongRepository().exists(input.get("name"), input.get("author"));
        if(exists) {
            return "Song already exists";
        }

        getSongRepository().add(new Song(name, author));
        return "Pesnicka %s a bola pridana do Databazy pesniciek.".formatted(name);
    }
}
