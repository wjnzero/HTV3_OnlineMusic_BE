package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends PagingAndSortingRepository<PlayList,Long> {
    @Query(nativeQuery = true,value = "select`playlist`.`name` from playlist  where `playlist`.`user_id` = :id ;")
    Iterable<IPlaylist> getPlayListByUser(@Param("id") Long id);
}
