package com.example.moviedatabase.service;

import com.example.moviedatabase.dto.MoviesDeleteDto;
import com.example.moviedatabase.dto.MoviesUpdateDto;
import com.example.moviedatabase.dto.MoviesDto;
import com.example.moviedatabase.dto.MoviesUploadDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MoviesService {
    public MoviesDto saveMovie(MoviesUploadDto moviesUploadDtoDto);
    public MoviesDto updateMovie(MoviesUpdateDto moviesUpdateDto);
    public boolean deleteMovie(MoviesDeleteDto moviesDeleteDto);
    public List<MoviesDto> getAllMovies();
}
