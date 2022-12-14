package com.htv3.htv3onlinemusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "playlist")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String timeCreate;
    private String lastTimeEdit;

    @Value("0")
    private Long viewPlaylist;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;


}
