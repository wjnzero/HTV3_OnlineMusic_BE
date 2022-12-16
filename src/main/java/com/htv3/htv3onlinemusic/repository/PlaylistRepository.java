package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends PagingAndSortingRepository<PlayList,Long> {
    @Query(nativeQuery = true,value = "select * from playlist  where `playlist`.`user_id` = :id ;")
    Iterable<PlayList> getPlayListByUser(@Param("id") Long id);

    List<PlayList> findAllByUsers(Long uid);

    @Query(value="select * from  playlist pl where pl.name like %:name%", nativeQuery=true)
    Iterable<PlayList> findByNamePlaylistContaining(@Param("name")String name);
}
