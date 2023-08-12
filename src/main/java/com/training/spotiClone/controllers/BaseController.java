package com.training.spotiClone.controllers;

import com.training.spotiClone.repositors.IPlaylistRepository;
import com.training.spotiClone.repositors.ISongRepository;
import com.training.spotiClone.repositors.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseController {
    @Autowired
    @Qualifier("listPlaylistRepository")
    private IPlaylistRepository playlistRepository;
    @Autowired
    @Qualifier("listSongRepository")
    private ISongRepository songRepository;
    @Autowired
    @Qualifier("jdbcUserRepository")
    private IUserRepository userRepository;

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public IPlaylistRepository getPlaylistRepository() {
        return playlistRepository;
    }

    public ISongRepository getSongRepository() {
        return songRepository;
    }
}
