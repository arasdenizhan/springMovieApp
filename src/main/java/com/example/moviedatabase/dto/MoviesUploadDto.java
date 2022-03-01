package com.example.moviedatabase.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MoviesUploadDto {
    @NotEmpty
    private String movieName;
    @NotEmpty
    private String movieDescription;
    @NotEmpty
    private String moviePicture;
}
