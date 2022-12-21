package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.entity.Song;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface SongRepository extends PagingAndSortingRepository<Song, Long> {


    @Query(nativeQuery = true,value = "select * from song  where `song`.`user_id` = :id  ;")
    Iterable<Song> getSongByUser(@Param("id") Long id);

    @Query(value = "select * from  song s where s.name like %:name%", nativeQuery = true)
    Iterable<Song> findByNameContaining(@Param("name") String name);

    @Query(value = "select * from song s join author a on s.author_id = a.id where a.name LIKE ?1", nativeQuery = true)
    Iterable<Song> findSongByAuthorNameContaining(String author);

    @Query(value = "select * from song s join singer sg on  s.singer_id= sg.id where sg.name LIKE %:singer%", nativeQuery = true)
    Iterable<Song> findSongBySingerContaining(@Param("singer") String singer);

    @Query(nativeQuery = true, value = "select  *  from song join playlist_of_song on `song`.`id` = playlist_of_song.song_id where playlist_of_song.playlist_id = :id")
    Iterable<Song> getSongInPlaylist(@Param("id") Long id);

    @Query(value = "select * from song order by date_create_song desc", nativeQuery = true)
    Iterable<Song> getSongNewest();

    @Query(value = "select * from song order by view_song desc", nativeQuery = true)
    Iterable<Song> getSongSortByView();

    @Modifying
    @Query(value = "update song set view_song = view_song + 1 where id = :idSong", nativeQuery = true)
    void increaseViewSong(@Param("idSong") Long idSong);

}

