package com.htv3.htv3onlinemusic.service.comment;

import com.htv3.htv3onlinemusic.model.Comment;
import com.htv3.htv3onlinemusic.service.GenericService;
import org.springframework.data.repository.query.Param;

public interface ICommentService extends GenericService<Comment> {
    Iterable<Comment> getCommentBySongId(Long id);

    Iterable<Comment> getCommentByPlaylistId(Long id);
}
