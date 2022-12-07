package com.htv3.htv3onlinemusic.controller;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("song")
public class SongController {
    @Autowired
    private ISongService songService;

    @Value("${upload.path}")
    private String fileUpload;
    @GetMapping("/")
    public ResponseEntity<Iterable<Song>> findAllSong(){
        Iterable<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

}
