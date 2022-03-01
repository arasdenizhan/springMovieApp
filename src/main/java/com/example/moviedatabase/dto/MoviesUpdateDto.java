package com.example.moviedatabase.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoviesUpdateDto {
    @NotEmpty
    private String movieId;
    private String newName;
    private String newDescription;
}
