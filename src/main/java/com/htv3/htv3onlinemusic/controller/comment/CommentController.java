package com.htv3.htv3onlinemusic.controller.comment;

import com.htv3.htv3onlinemusic.model.Comment;
import com.htv3.htv3onlinemusic.service.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/song/{idSong}")
    public ResponseEntity<Iterable<Comment>> getAllCommentBySongId(@PathVariable("idSong") Long idSong) {
        return new ResponseEntity<>(commentService.getCommentBySongId(idSong), HttpStatus.OK);
    }

}
