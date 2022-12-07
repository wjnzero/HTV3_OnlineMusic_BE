package com.htv3.htv3onlinemusic.controller;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("songs")
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
    @PostMapping("/create")
    public ResponseEntity<Song> createSong(@RequestBody Song song){
        songService.save(song);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id,@RequestBody Song song){
        Optional<Song> song1 = songService.findById(id);
        if (!song1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        song.setId(song1.get().getId());
        return new ResponseEntity<>(songService.save(song), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Song> findByID(@PathVariable Long id){
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song.get() , HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Song> deleteCourse(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.remove(id);
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }
}
