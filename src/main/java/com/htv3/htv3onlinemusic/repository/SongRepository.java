package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song,Long> {


    @Query(nativeQuery = true,value = "select`song`.`name`,`song`.`avatar`,`song`.`view` from song  where `song`.`user_id` = :id ;")
    Iterable<ISong> getSongByUser(@Param("id") Long id);

    @Query(value="select * from  song s where s.name like %:name%", nativeQuery=true)
    Iterable<Song> findByNameContaining(@Param("name")String name);

    @Query(value="select * from  song s where s.author like %:author%", nativeQuery=true)
    Iterable<Song> findByAuthorContaining(@Param("author")String author);

    @Query(value="select s.id,s.name from song s join singer sg on s.singer_id = sg.id where sg.name LIKE '%singer%'", nativeQuery=true)
    Iterable<Song> findBySingerContaining(@Param("singer")String singer);

    @Query(value = "select * from song where name_create like ?1", nativeQuery = true)
    Iterable<Song> findSongByNameCreate (@PathVariable String name_create);
}

