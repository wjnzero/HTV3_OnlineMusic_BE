package com.htv3.htv3onlinemusic.service.song;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import com.htv3.htv3onlinemusic.service.GenericService;




public interface ISongService extends GenericService<Song> {

    Iterable<ISong> findSongByUser(Long id);
    Iterable<Song> findSongInPlaylist(Long id);

    Iterable<Song> findByNameContaining(String name);

    Iterable<Song> findSongByAuthorContaining(String author);

    Iterable<Song> findSongBySingerContaining(String singer);

    Iterable<Song> getSongNewest();
}
