package com.htv3.htv3onlinemusic.service.playlist;

import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPlaylistService extends GenericService<PlayList> {
    Iterable<PlayList> findPlaylistByUser(Long id);
    List<PlayList> findAllPlaylistByUser(Long id);

    Iterable<PlayList> findByNamePlaylistContaining(String name);
}
