package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.entity.PlayList;
import com.htv3.htv3onlinemusic.model.entity.Song;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PlaylistRepository extends PagingAndSortingRepository<PlayList, Long> {
    @Query(nativeQuery = true, value = "select * from playlist  where `playlist`.`user_id` = :id ;")
    Iterable<PlayList> getPlayListByUser(@Param("id") Long id);

    List<PlayList> findAllByUsers(Long uid);

    @Query(value = "select * from  playlist pl where pl.name like %:name%", nativeQuery = true)
    Iterable<PlayList> findByNamePlaylistContaining(@Param("name") String name);

    //    @Modifying
//    @Query(value = "update playlist set like_playlist = like_playlist + 1 where id = :idPlaylist", nativeQuery = true)
//    void increaseLikePlaylist(@Param("idPlaylist") Long idPlaylist);
//
//    @Modifying
//    @Query(value = "update playlist set like_playlist = like_playlist - 1 where id = :idPlaylist", nativeQuery = true)
//    void decreaseLikePlaylist(@Param("idPlaylist") Long idPlaylist);
    @Query(value = "select * from playlist order by time_create desc", nativeQuery = true)
    Iterable<PlayList> getPlaylistNewest();

    @Query(value = "select * from playlist order by view_playlist desc", nativeQuery = true)
    Iterable<PlayList> getPlaylistSortByView();

    @Query(value = "select * from playlist join user_like on playlist.id = user_like.playlist_id  group by id Order By count(id) desc", nativeQuery = true)
    Iterable<PlayList> getPlaylistSortByLike();

    @Modifying
    @Query(value = "update playlist set view_playlist = view_playlist + 1 where id = :idPlaylist", nativeQuery = true)
    void increaseViewPlaylist(@Param("idPlaylist") Long idPlaylist);
}
