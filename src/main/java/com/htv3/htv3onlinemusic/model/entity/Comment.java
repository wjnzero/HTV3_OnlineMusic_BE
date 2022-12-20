package com.htv3.htv3onlinemusic.model.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment_content;

    private LocalDate dateCreate;

    @ManyToOne
    @JoinColumn(name = "user_id_comment")
    private User userComment;
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song songComment;
    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private PlayList playListComment;

}
