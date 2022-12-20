package com.htv3.htv3onlinemusic.service.comment;

import com.htv3.htv3onlinemusic.model.entity.Comment;
import com.htv3.htv3onlinemusic.service.GenericService;

public interface ICommentService extends GenericService<Comment> {
    Iterable<Comment> getCommentBySongId(Long id);

    Iterable<Comment> getCommentByPlaylistId(Long id);
}
