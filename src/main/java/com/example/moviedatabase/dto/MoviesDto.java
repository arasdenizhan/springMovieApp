package com.example.moviedatabase.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MoviesDto {
    private Long id;
    private String movieName;
    private String movieDescription;
    private byte[] moviePicture;
}
