package com.training.spotiClone;

import com.training.spotiClone.dao.Playlist;
import com.training.spotiClone.dao.User;
import com.training.spotiClone.dao.UserType;
import com.training.spotiClone.repositors.IPlaylistRepository;
import com.training.spotiClone.repositors.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class SpotiCloneApplicationTests {

	@Autowired
	@Qualifier("listPlaylistRepository")
	IPlaylistRepository playlistRepository;

	@Autowired
	@Qualifier("listUserRepository")
	IUserRepository userRepository;

	@Test
	void testCreatePlaylist() {
		User user = new User("Ali", "Menty", UserType.PREMIUM);
		userRepository.add(user);

		playlistRepository.addPlaylist(new Playlist(user.getId(), "Best"));
		Assertions.assertTrue(playlistRepository.getPlaylistById(1).isPresent());
	}
}
