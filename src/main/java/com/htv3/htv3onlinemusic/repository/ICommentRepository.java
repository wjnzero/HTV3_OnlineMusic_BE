package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.Comment;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM comment where song_id = :id")
    Iterable<Comment> getCommentBySongId(@Param("id") Long id);


}
