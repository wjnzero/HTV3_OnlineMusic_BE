package com.htv3.htv3onlinemusic.service.song;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import com.htv3.htv3onlinemusic.service.GenericService;

import java.util.Optional;

public interface ISongService extends GenericService<Song> {

    Iterable<Song> findSongByName(String name);
    Iterable<ISong> findSongByUser(Long id);

    Iterable<Song> findByNameContaining(String name);

}
