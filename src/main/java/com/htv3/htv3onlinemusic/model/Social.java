package com.htv3.htv3onlinemusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "social")
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String view;

    @OneToOne
    @JoinColumn(name = "song_id")
    private Song song  ;
}
