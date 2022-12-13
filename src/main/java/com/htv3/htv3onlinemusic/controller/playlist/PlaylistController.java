package com.htv3.htv3onlinemusic.controller.playlist;


import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.User;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.repository.PlaylistRepository;
import com.htv3.htv3onlinemusic.service.playlist.IPlaylistService;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<Iterable<PlayList>> findAllPlaylist(){
        Iterable<PlayList> playLists = playlistService.findAll();
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }
    @GetMapping("/findPlaylistByUser/{id}")
    public ResponseEntity<Iterable<IPlaylist>> findPlaylistByUser(@PathVariable Long id){
        Iterable<IPlaylist> playLists = playlistService.findPlaylistByUser(id);
        return new ResponseEntity<>(playLists,HttpStatus.OK);
    }
    @PostMapping("/create/{id}")
    public ResponseEntity<PlayList> createPlaylist(@PathVariable Long id,@RequestBody PlayList playList ){
        playList.setUsers(userService.findById(id).get());
        playList.setName(playList.getName());
        playlistService.save(playList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<PlayList> updatePlaylist(@PathVariable Long id, @RequestBody PlayList playList){
        Optional<PlayList> playLists = playlistService.findById(id);
        if (!playLists.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        (playLists.get()).setName(playList.getName());
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
    public ResponseEntity<PlayList> deleteCourse(@PathVariable Long id) {
        Optional<PlayList> playList = playlistService.findById(id);
        if (!playList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playlistService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
