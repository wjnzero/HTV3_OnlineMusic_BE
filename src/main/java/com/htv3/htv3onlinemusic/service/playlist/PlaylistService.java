package com.htv3.htv3onlinemusic.service.playlist;

import com.htv3.htv3onlinemusic.model.entity.PlayList;
import com.htv3.htv3onlinemusic.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public PlayList save(PlayList playList) {
        return playlistRepository.save(playList);
    }

    @Override
    public void remove(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Iterable<PlayList> findPlaylistByUser(Long id) {
        return playlistRepository.getPlayListByUser(id);
    }

    @Override
    public List<PlayList> findAllPlaylistByUser(Long id) {
        return playlistRepository.findAllByUsers(id);
    }


    @Override
    public Iterable<PlayList> findByNamePlaylistContaining(String name) {
        return playlistRepository.findByNamePlaylistContaining(name);
    }

    @Override
    public Iterable<PlayList> getPlaylistNewest() {
        return playlistRepository.getPlaylistNewest();
    }

    @Override
    public Iterable<PlayList> getPlaylistSortByView() {
        return playlistRepository.getPlaylistSortByView();
    }

    @Override
    public Iterable<PlayList> getPlaylistSortByLike() {
        return playlistRepository.getPlaylistSortByLike();
    }

    @Override
    public void increaseViewPlaylist(Long idPlaylist) {
        playlistRepository.increaseViewPlaylist(idPlaylist);
    }


//    @Override
//    public void increaseLikePlaylist(Long idPlaylist) {
//        playlistRepository.increaseLikePlaylist(idPlaylist);
//
//    }
//
//    @Override
//    public void decreaseLikePlaylist(Long idPlaylist) {
//        playlistRepository.decreaseLikePlaylist(idPlaylist);
//    }
}
