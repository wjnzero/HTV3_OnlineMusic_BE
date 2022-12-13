package com.htv3.htv3onlinemusic.service.song;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import com.htv3.htv3onlinemusic.service.GenericService;
import org.springframework.web.bind.annotation.PathVariable;


public interface ISongService extends GenericService<Song> {

    Iterable<ISong> findSongByUser(Long id);

    Iterable<Song> findByNameContaining(String name);

    Iterable<Song> findSongByNameCreate (@PathVariable String name_create);

}
