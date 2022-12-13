package com.htv3.htv3onlinemusic.service.song;

import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.ISong;
import com.htv3.htv3onlinemusic.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Iterable<ISong> findSongByUser(Long id) {
        return songRepository.getSongByUser(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
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
    public Iterable<Song> findSongByNameCreate(String name_create) {
        return songRepository.findSongByNameCreate("%"+name_create+"%");
    }
}
