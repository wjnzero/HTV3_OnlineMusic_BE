package com.htv3.htv3onlinemusic.controller.playlist;


import com.htv3.htv3onlinemusic.model.entity.PlayList;
import com.htv3.htv3onlinemusic.model.entity.Song;
import com.htv3.htv3onlinemusic.model.entity.User;
import com.htv3.htv3onlinemusic.service.playlist.IPlaylistService;
import com.htv3.htv3onlinemusic.service.song.ISongService;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<Iterable<PlayList>> findPlaylistByUser(@PathVariable Long id) {
        Iterable<PlayList> playLists = playlistService.findPlaylistByUser(id);
        return new ResponseEntity<>(playLists, HttpStatus.OK);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<PlayList> createPlaylist(@PathVariable Long id, @RequestBody PlayList playList) {
        playList.setUsers(userService.findById(id).get());
        playList.setName(playList.getName());
//        playList.setLikePlaylist(0L);
        playList.setViewPlaylist(0L);
        playlistService.save(playList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlayList> updatePlaylist(@PathVariable Long id, @RequestBody PlayList playList) {
        Optional<PlayList> checkPlaylist = playlistService.findById(id);
        if (!checkPlaylist.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Date date = new Date();
        PlayList rs = checkPlaylist.orElse(null);
        rs.setName(playList.getName());
        rs.setLastTimeEdit(LocalDate.now(ZoneId.systemDefault()));
        playlistService.save(rs);
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
    public ResponseEntity<PlayList> deleteSongInPlaylist(@PathVariable Long id, @RequestBody Song song) {
        Optional<Song> songs = songService.findById(id);
        playlistService.remove(songs.get().getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<PlayList>> findByNamePlaylistContaining(@RequestParam("name") String name) {
        return new ResponseEntity<>(playlistService.findByNamePlaylistContaining(name), HttpStatus.OK);
    }

    // Phần like của Playlist
    @GetMapping("/allLike/{idPlaylist}")
    public ResponseEntity<Integer> countLikePlaylist(@PathVariable("idPlaylist") Long idPlaylist) {
        return new ResponseEntity<>(playlistService.findById(idPlaylist).get().getUsersLike().size(), HttpStatus.OK);
    }

    @GetMapping("/checkLike/{idPlaylist}/{idUser}")
    public ResponseEntity<Boolean> CheckLikePlaylist(@PathVariable Long idPlaylist, @PathVariable Long idUser) {
        PlayList playList = playlistService.findById(idPlaylist).get();
        User user = userService.findById(idUser).get();
        Set<User> userSet = playList.getUsersLike();
        return new ResponseEntity<>(userSet.contains(user), HttpStatus.OK);
    }

    @GetMapping("/like/{idPlaylist}/{idUser}")
    public ResponseEntity<?> increaseLikePlaylist(@PathVariable Long idPlaylist, @PathVariable Long idUser) {
        PlayList playList = playlistService.findById(idPlaylist).get();
        User user = userService.findById(idUser).get();
        Set<User> userSet = playList.getUsersLike();
        userSet.add(user);
        playList.setUsersLike(userSet);
        playlistService.save(playList);
        return new ResponseEntity<>(playlistService.findById(idPlaylist).get().getUsersLike().size(), HttpStatus.OK);
    }


    @GetMapping("/unlike/{idPlaylist}/{idUser}")
    public ResponseEntity<?> decreaseLikePlaylist(@PathVariable Long idPlaylist, @PathVariable Long idUser) {
        PlayList playList = playlistService.findById(idPlaylist).get();
        User user = userService.findById(idUser).get();
        Set<User> userSet = playList.getUsersLike();
        userSet.remove(user);
        playList.setUsersLike(userSet);
        playlistService.save(playList);
        return new ResponseEntity<>(playlistService.findById(idPlaylist).get().getUsersLike().size(),HttpStatus.OK);
    }
    @GetMapping("/newest")
    public ResponseEntity<Iterable<PlayList>> getPlaylistNewest(){
        return new ResponseEntity<>(playlistService.getPlaylistNewest(), HttpStatus.OK);
    }

    @GetMapping("/mostview")
    public ResponseEntity<Iterable<PlayList>> getPlaylistSortByView(){
        return new ResponseEntity<>(playlistService.getPlaylistSortByView(), HttpStatus.OK);
    }
    @GetMapping("/mostlike")
    public ResponseEntity<Iterable<PlayList>> getPlaylistSortByLike(){
        return new ResponseEntity<>(playlistService.getPlaylistSortByLike(), HttpStatus.OK);
    }
    @GetMapping("/view/{idPlaylist}")
    public ResponseEntity<?> increaseViewPlaylist(@PathVariable("idPlaylist") Long idPlaylist) {
        playlistService.increaseViewPlaylist(idPlaylist);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}