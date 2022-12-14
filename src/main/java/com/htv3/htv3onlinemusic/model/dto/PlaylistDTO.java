package com.htv3.htv3onlinemusic.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaylistDTO {
    private Long id;
    private String name;
    private String describe_playlist;
//    private String avatar;
//    private String album;
}
