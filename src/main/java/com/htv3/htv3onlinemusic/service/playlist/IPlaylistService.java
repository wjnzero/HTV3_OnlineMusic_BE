package com.htv3.htv3onlinemusic.service.playlist;

import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.service.GenericService;
import org.springframework.stereotype.Service;

@Service
public interface IPlaylistService extends GenericService<PlayList> {
    Iterable<IPlaylist> findPlaylistByUser(Long id);

    Iterable<PlayList> findByNamePlaylistContaining(String name);
}
