package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM comment where song_id = :id order by date_create desc")
    Iterable<Comment> getCommentBySongId(@Param("id") Long id);

    @Query(nativeQuery = true,value = "SELECT * FROM comment where playlist_id = :id order by date_create desc")
    Iterable<Comment> getCommentByPlaylistId(@Param("id") Long id);


}
