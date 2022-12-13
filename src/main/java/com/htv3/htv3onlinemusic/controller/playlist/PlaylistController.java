package com.htv3.htv3onlinemusic.controller.playlist;


import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.repository.PlaylistRepository;
import com.htv3.htv3onlinemusic.service.playlist.IPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private IPlaylistService playlistService;

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

}
