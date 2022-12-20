package com.htv3.htv3onlinemusic.service.song;

import com.htv3.htv3onlinemusic.model.entity.Song;
import com.htv3.htv3onlinemusic.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SongService implements ISongService {
    @Autowired
    SongRepository songRepository;

    @Override
    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    @Override
    public Iterable<Song> findSongByUser(Long id) {
        return songRepository.getSongByUser(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    @Transactional
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void remove(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Iterable<Song> findByNameContaining(String name) {
        return songRepository.findByNameContaining(name);
    }

    @Override
    public Iterable<Song> findSongByAuthorNameContaining(String author) {
        return songRepository.findSongByAuthorNameContaining(author);
    }

    @Override
    public Iterable<Song> findSongBySingerContaining(String singer) {
        return songRepository.findSongBySingerContaining(singer);
    }

    @Override
    public Iterable<Song> getSongNewest() {
        return songRepository.getSongNewest();
    }

    @Override
    public Iterable<Song> getSongSortByView() {
        return songRepository.getSongSortByView();
    }

    @Override
    public void increaseViewSong(Long idSong) {
        songRepository.increaseViewSong(idSong);
    }

    @Override
    public Iterable<Song> findSongInPlaylist(Long id) {
        return songRepository.getSongInPlaylist(id);
    }

//    @Override
//    public Iterable<Song> countSongByViewSong(Long id){
//        return songRepository.countSongByViewSong(id);
//    }
}
