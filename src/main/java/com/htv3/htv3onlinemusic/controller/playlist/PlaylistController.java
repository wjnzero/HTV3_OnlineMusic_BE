package com.htv3.htv3onlinemusic.controller.playlist;


import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.service.playlist.IPlaylistService;
import com.htv3.htv3onlinemusic.service.song.ISongService;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;

    @Autowired
    private ISongService songService;
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<Iterable<PlayList>> findAllPlaylist() {
        Iterable<PlayList> playLists = playlistService.findAll();
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }

    @GetMapping("/findPlaylistByUser/{id}")
    public ResponseEntity<Iterable<IPlaylist>> findPlaylistByUser(@PathVariable Long id) {
        Iterable<IPlaylist> playLists = playlistService.findPlaylistByUser(id);
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<PlayList> createPlaylist(@PathVariable Long id, @RequestBody PlayList playList) {
        Date date = new Date();
        playList.setUsers(userService.findById(id).get());
        playList.setName(playList.getName());
        playList.setTimeCreate(String.valueOf(new Timestamp(date.getTime())));
        playlistService.save(playList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlayList> updatePlaylist(@PathVariable Long id, @RequestBody PlayList playList) {
        Optional<PlayList> playLists = playlistService.findById(id);
        if (!playLists.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Date date = new Date();
        (playLists.get()).setName(playList.getName());
        playList.setLastTimeEdit(String.valueOf(new Timestamp(date.getTime())));
        playlistService.save(playLists.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayList> findByID(@PathVariable Long id) {
        Optional<PlayList> playList = playlistService.findById(id);
        if (!playList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playList.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PlayList> deletePlaylist(@PathVariable Long id) {
        Optional<PlayList> playList = playlistService.findById(id);
        if (!playList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletesong/{id}")
    public ResponseEntity<PlayList> deleteSongInPlaylist(@PathVariable Long id,@RequestBody Song song){
        Optional<Song> songs = songService.findById(id);

        playlistService.remove(songs.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/search")
    public ResponseEntity<Iterable<PlayList>> findByNamePlaylistContaining(@RequestParam("name") String name) {
        return new ResponseEntity<>(playlistService.findByNamePlaylistContaining(name), HttpStatus.OK);
    }
}