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

    private String nameCreate;

    @ManyToMany
    @JoinColumn(name = "type_id")
    private Set<SongType> songTypeSet;

    @ManyToMany
    @JoinTable(name = "album_song", joinColumns = {@JoinColumn(name = "album_id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id")})
    private Set<Album> albumSet;
    @OneToOne
    @JoinColumn(name = "social_id")
    private Social social;

}
