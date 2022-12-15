package com.htv3.htv3onlinemusic.controller.song;

import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.User;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import com.htv3.htv3onlinemusic.service.playlist.IPlaylistService;
import com.htv3.htv3onlinemusic.service.song.ISongService;
import com.htv3.htv3onlinemusic.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private ISongService songService;
    @Autowired
    private IPlaylistService playlistService;
    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public ResponseEntity<Iterable<Song>> findAllSong() {
        Iterable<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/findByUser/{id}")
    public ResponseEntity<Iterable<Song>> findSongByUser(@PathVariable Long id) {
        Iterable<Song> songs = songService.findSongByUser(id);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
    @GetMapping("/findsonginplaylist/{id}")
    public ResponseEntity<Iterable<Song>> findSongInPlaylist(@PathVariable Long id) {
        Iterable<Song> songs = songService.findSongInPlaylist(id);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

//    @DeleteMapping("/{id}/findsonginplaylist/{id1}")
//    public ResponseEntity<Iterable<Song>> deleteSongInPlaylist(@PathVariable Long id, @PathVariable Long id1) {
//        Iterable<Song> songs = songService.findSongInPlaylist(id);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Song> createSong(@PathVariable Long id, @RequestBody Song song) {
        try {
            song.setUser(userService.findById(id).get());
            song.setName(song.getName());
            song.setDescribeSong(song.getDescribeSong());
            song.setFileMp3(song.getFileMp3());
            song.setAvatar(song.getAvatar());
            songService.save(song);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/{id}/playlist/{idPlay}")
    public ResponseEntity<Song> createSongInPlaylist(@PathVariable Long id, @PathVariable Long idPlay) {
        try {
            Song song1 = songService.findById(id).get();
            PlayList playList = playlistService.findById(idPlay).get();
            Set<PlayList> playListSet = new HashSet<>();
            playListSet = song1.getPlayLists();
            playListSet.add(playList);
            song1.setPlayLists(playListSet);
            songService.save(song1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @@DeleteMapping("/{id}/playlist/{idPlay}")
//    public ResponseEntity<Song> deleteSongInPlaylist(@PathVariable Long id, @PathVariable Long idPlay) {
//        try {
//            Song song1 = songService.findById(id).get();
//            PlayList playList = playlistService.findById(idPlay).get();
//            Set<PlayList> playListSet = new HashSet<>();
//            playListSet = song1.getPlayLists();
//            playListSet.add(playList);
//            song1.setPlayLists(playListSet);
//            songService.remove(song1);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song song) {
        Optional<Song> song1 = songService.findById(id);
        if (!song1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        song1.get().setName(song.getName());
        song1.get().setDescribeSong(song.getDescribeSong());
        song1.get().setAvatar(song.getAvatar());
        song1.get().setFileMp3(song.getFileMp3());
        song1.get().setLastTimeEdit(song.getLastTimeEdit());
        songService.save(song1.get());
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> findByID(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(song.get(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Song> deleteCourse(@PathVariable Long id) {
        Optional<Song> song = songService.findById(id);
        if (!song.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        songService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Song>> findByNameContaining(@RequestParam("name") String name) {
        return new ResponseEntity<>(songService.findByNameContaining(name), HttpStatus.OK);
    }

    //    @GetMapping("/search/author")
//    public ResponseEntity<Iterable<Song>> findSongByAuthorContaining(@RequestParam("author") String author) {
//        Iterable<Song> s = songService.findSongByAuthorContaining(author);
//        return new ResponseEntity<>(songService.findSongByAuthorContaining(author), HttpStatus.OK);
//    }
    @GetMapping("/search/singer")
    public ResponseEntity<Iterable<Song>> findSongBySingerContaining(@RequestParam("singer") String singer) {
        return new ResponseEntity<>(songService.findSongBySingerContaining(singer), HttpStatus.OK);
    }

    @GetMapping("/newest")
    public ResponseEntity<Iterable<Song>> getSongNewest(){
        return new ResponseEntity<>(songService.getSongNewest(), HttpStatus.OK);
    }
}