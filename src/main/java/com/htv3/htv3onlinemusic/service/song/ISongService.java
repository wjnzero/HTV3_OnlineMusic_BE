package com.htv3.htv3onlinemusic.service.song;

import com.htv3.htv3onlinemusic.model.entity.Song;
import com.htv3.htv3onlinemusic.service.GenericService;


public interface ISongService extends GenericService<Song> {

    Iterable<Song> findSongByUser(Long id);
    Iterable<Song> findSongInPlaylist(Long id);

    Iterable<Song> findByNameContaining(String name);

    Iterable<Song> findSongByAuthorNameContaining(String author);

    Iterable<Song> findSongBySingerContaining(String singer);

//    Iterable<Song> countSongByViewSong(Long id);

    Iterable<Song> getSongNewest();
    Iterable<Song> getSongSortByView();

    void increaseViewSong(Long idSong);
}
