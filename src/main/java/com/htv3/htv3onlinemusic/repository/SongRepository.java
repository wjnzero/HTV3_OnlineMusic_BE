package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song,Long> {

    Iterable<Song> findSongByName(String name);

    @Query(nativeQuery = true,value = "select`song`.`name`,`song`.`avatar`,`song`.`view` from song  where `song`.`user_id` = :id ;")
    Iterable<ISong> getSongByUser(@Param("id") Long id);

    @Query(value="select * from song s where s.name like % name % ", nativeQuery=true)
    Iterable<Song> findByNameContaining(String name);

}
