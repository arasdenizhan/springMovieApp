package com.example.moviedatabase.controller;

import com.example.moviedatabase.dto.MoviesDeleteDto;
import com.example.moviedatabase.dto.MoviesUpdateDto;
import com.example.moviedatabase.dto.MoviesDto;
import com.example.moviedatabase.dto.MoviesUploadDto;
import com.example.moviedatabase.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/movies")
public class MoviesController {
    private static final String MOVIE_NULL_MESSAGE = "Movie cannot be null!";
    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService){
        this.moviesService = moviesService;
    }

    @PostMapping
    public ResponseEntity<MoviesDto> addMovie(@Valid @RequestBody MoviesUploadDto moviesUploadDtoDto){
        Objects.requireNonNull(moviesUploadDtoDto, MOVIE_NULL_MESSAGE);
        MoviesDto moviesDto = moviesService.saveMovie(moviesUploadDtoDto);
        if(!Objects.isNull(moviesDto)){
            return ResponseEntity.ok(moviesDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<MoviesDto> updateMovie(@Valid @RequestBody MoviesUpdateDto moviesUpdateDto){
        Objects.requireNonNull(moviesUpdateDto, MOVIE_NULL_MESSAGE);
        MoviesDto moviesDto = moviesService.updateMovie(moviesUpdateDto);
        if(!Objects.isNull(moviesDto)){
            return ResponseEntity.ok(moviesDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<MoviesDto> deleteMovie (@Valid @RequestBody MoviesDeleteDto moviesDeleteDto) {
        Objects.requireNonNull(moviesDeleteDto, MOVIE_NULL_MESSAGE);
        boolean operationStatus = moviesService.deleteMovie(moviesDeleteDto);
        if(operationStatus){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public ResponseEntity<List<MoviesDto>> getAllMovies(){
        return ResponseEntity.ok(moviesService.getAllMovies());
    }

}
