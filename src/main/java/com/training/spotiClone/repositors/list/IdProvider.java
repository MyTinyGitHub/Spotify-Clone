package com.training.spotiClone.repositors.list;

import org.springframework.stereotype.Service;

@Service
public class IdProvider {
    public static long songId = 1000L;
    public static long userId = 1000L;

    public static Long generateSongId() {
        return songId++;
    }

    public static Long generateUserId() {
        return userId++;
    }
}
