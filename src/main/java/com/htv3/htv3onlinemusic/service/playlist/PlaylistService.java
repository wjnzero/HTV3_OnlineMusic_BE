package com.htv3.htv3onlinemusic.service.playlist;

import com.htv3.htv3onlinemusic.model.PlayList;
import com.htv3.htv3onlinemusic.model.Song;
import com.htv3.htv3onlinemusic.model.dto.IPlaylist;
import com.htv3.htv3onlinemusic.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService implements IPlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Override
    public Iterable<PlayList> findAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Optional<PlayList> findById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public PlayList save(PlayList playList) {
        return playlistRepository.save(playList);
    }

    @Override
    public void remove(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Iterable<IPlaylist> findPlaylistByUser(Long id) {
        return playlistRepository.getPlayListByUser(id);
    }

    @Override
    public Iterable<PlayList> findByNamePlaylistContaining(String name) {
        return playlistRepository.findByNamePlaylistContaining(name);
    }
}
