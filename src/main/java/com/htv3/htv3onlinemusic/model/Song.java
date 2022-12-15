package com.htv3.htv3onlinemusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dateCreateSong;

    @OneToMany
    @JoinColumn(name = "song_id")
    private Set<Comment> comment;

    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany
    @JoinColumn(name = "singer_id")
    private Set<Singer> singer;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "playlist_of_song", joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "playlist_id")})
    private Set<PlayList> playLists;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "type_of_song", joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = {@JoinColumn(name = "type_id")})
    private Set<SongType> songTypes;

    private String album;

    @Value("0")
    private Long viewSong;
}
