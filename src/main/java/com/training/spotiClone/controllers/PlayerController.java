package com.training.spotiClone.controllers;

import com.training.spotiClone.dao.Song;
import com.training.spotiClone.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
public class PlayerController extends BaseController {
    @Autowired
    PlayerStatisticsController playerStatistics;

    @GetMapping("/play/{songId}")
    public String playSong(@PathVariable Map<String, Long> input) {
        Long songId = input.getOrDefault("songId", -1L);
        Optional<Song> song = getSongRepository().getSongById(songId);

        playAd(input);

        return song.isPresent() ? song.toString() : "No song with { %s } id".formatted(songId);
    }

    @GetMapping("/play/random/")
    public String playRandom(@RequestParam Map<String, Long> input) {
        Optional<Song> song = getSongRepository().getRandomSong();

        playAd(input);

        return song.isPresent() ? song.toString() : "No songs are available";
    }

    private void playAd(Map<String, Long> input) {
        long userId = input.getOrDefault("userId" , -1L);
        User user = getUserRepository().getUserById(userId);
        playerStatistics.playAd(user);
    }
}
