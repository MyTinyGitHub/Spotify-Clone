DELETE FROM USERS;
DELETE FROM SONG;
DELETE FROM PLAYLIST;

INSERT INTO USERS (id, user_name, password, type) VALUES(1, 'test_regular', 'test', 1);
INSERT INTO USERS (id, user_name, password, type) VALUES(2, 'test_premium', 'test', 2);

INSERT INTO SONG (id, name, author) VALUES(1, 'Metalica1', 'Metalica');
INSERT INTO SONG (id, name, author) VALUES(2, 'Metalica2', 'Metalica');
INSERT INTO SONG (id, name, author) VALUES(3, 'Metalica3', 'Metalica');
INSERT INTO SONG (id, name, author) VALUES(4, 'Metalica4', 'Metalica');
INSERT INTO SONG (id, name, author) VALUES(5, 'HU1', 'Hollywood Undead');
INSERT INTO SONG (id, name, author) VALUES(6, 'HU2', 'Hollywood Undead');
INSERT INTO SONG (id, name, author) VALUES(7, 'HU3', 'Hollywood Undead');

INSERT INTO PLAYLIST(id, owner_id, name) VALUES( 1, 1, 'BEST');
INSERT INTO PLAYLIST(id, owner_id, name) VALUES( 2, 2, 'BEST PREMIUM PLAYLIST');

INSERT INTO playlistToSong (playlist_id, song_id) VALUES(1, 1);
INSERT INTO playlistToSong (playlist_id, song_id) VALUES(1, 3);
INSERT INTO playlistToSong (playlist_id, song_id) VALUES(1, 7);
INSERT INTO playlistToSong (playlist_id, song_id) VALUES(2, 3);
INSERT INTO playlistToSong (playlist_id, song_id) VALUES(2, 6);