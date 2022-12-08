package com.htv3.htv3onlinemusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String describeSong;
    private String fileMp3;
    private String avatar;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinColumn(name = "singer_id")
    private Set<Singer> singer;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "song_id")
    private Set<SongType> songTypeSet;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlayList playListSet;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "playlist_song", joinColumns = {@JoinColumn(name = "playlist_id")},
//            inverseJoinColumns = {@JoinColumn(name = "song_id")})
//    private Set<PlayList> playListSet;

    private String album;
    private String view;
}
