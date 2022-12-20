package com.htv3.htv3onlinemusic.controller.comment;

import com.htv3.htv3onlinemusic.model.entity.Comment;
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
    @PostMapping("/song")
    public ResponseEntity<Comment> createCommentSong(@RequestBody Comment commentSong) {
        commentService.save(commentSong);
        return new ResponseEntity<>(commentSong, HttpStatus.OK);
    }

    @GetMapping("/playlist/{idPlaylist}")
    public ResponseEntity<Iterable<Comment>> getAllCommentPlaylist(@PathVariable("idPlaylist") Long idPlaylist) {
        return new ResponseEntity<>(commentService.getCommentByPlaylistId(idPlaylist), HttpStatus.OK);
    }

    @PostMapping("/playlist")
    public ResponseEntity<Comment> createCommentPlaylist(@RequestBody Comment commentPlaylist) {
        commentService.save(commentPlaylist);
        return new ResponseEntity<>(commentPlaylist, HttpStatus.OK);
    }

}
