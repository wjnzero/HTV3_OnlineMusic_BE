package com.htv3.htv3onlinemusic.service.playlist;

import com.htv3.htv3onlinemusic.model.entity.PlayList;
import com.htv3.htv3onlinemusic.service.GenericService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPlaylistService extends GenericService<PlayList> {
    Iterable<PlayList> findPlaylistByUser(Long id);

    List<PlayList> findAllPlaylistByUser(Long id);

    Iterable<PlayList> findByNamePlaylistContaining(String name);

    //    void increaseLikePlaylist(Long idPlaylist);
//
//    void decreaseLikePlaylist(Long idPlaylist);
    Iterable<PlayList> getPlaylistNewest();

    Iterable<PlayList> getPlaylistSortByView();

    Iterable<PlayList> getPlaylistSortByLike();

    void increaseViewPlaylist(@Param("idPlaylist") Long idPlaylist);
}
