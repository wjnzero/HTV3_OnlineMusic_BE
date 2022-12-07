package com.htv3.htv3onlinemusic.repository;

import com.htv3.htv3onlinemusic.model.Song;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends PagingAndSortingRepository<Song,Long> {
}
