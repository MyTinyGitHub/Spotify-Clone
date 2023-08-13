create table if not exists Users (
    id identity,
    user_name varchar(50) not null,
    password varchar(50) not null,
    type int not null
);

create table if not exists Song (
    id identity,
    name varchar(50) not null,
    author varchar(50) not null
);

create table if not exists Playlist (
    id identity,
    owner_id integer,
    name varchar(50)
);

alter table Playlist
    add foreign key (owner_id) references Users(id);

create table if not exists PlaylistToSong (
    playlist_id integer,
    song_id integer
);

