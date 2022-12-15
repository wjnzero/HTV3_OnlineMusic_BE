package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface SongRepository extends PagingAndSortingRepository<Song,Long> {


    @Query(nativeQuery = true,value = "select`song`.`name`,`song`.`avatar`,`song`.`view` from song  where `song`.`user_id` = :id ;")
    Iterable<ISong> getSongByUser(@Param("id") Long id);

    @Query(value="select * from  song s where s.name like %:name%", nativeQuery=true)
    Iterable<Song> findByNameContaining(@Param("name")String name);

    @Query(value = "select * from song s join author a on s.author_id = a.id where a.name LIKE ?1" ,nativeQuery = true)
    Iterable<Song> findSongByAuthorNameContaining(String author);

    @Query(value = "select * from song join song_singer on  song.id = song_singer.song_id join singer on singer.id = song_singer.singer_id where singer.name LIKE %:singer%" ,nativeQuery = true)
    Iterable<Song> findSongBySingerContaining (String singer);

    @Query(nativeQuery = true,value = "select  *  from song join playlist_of_song on `song`.`id` = playlist_of_song.song_id where playlist_of_song.playlist_id = :id")
    Iterable<Song> getSongInPlaylist(@Param("id") Long id);

//    @Query(value="select * from  song where song.id = :id", nativeQuery=true)
//    Iterable<Song> countSongByViewSong(Long id);
}

