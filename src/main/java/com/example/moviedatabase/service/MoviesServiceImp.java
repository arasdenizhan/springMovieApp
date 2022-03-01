package com.example.moviedatabase.service;

import com.example.moviedatabase.dto.MoviesDeleteDto;
import com.example.moviedatabase.dto.MoviesUpdateDto;
import com.example.moviedatabase.dto.MoviesDto;
import com.example.moviedatabase.dto.MoviesUploadDto;
import com.example.moviedatabase.repository.MoviesRepository;
import com.example.moviedatabase.model.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoviesServiceImp implements MoviesService{

    private final MoviesRepository moviesRepository;

    @Autowired
    public MoviesServiceImp(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Override
    public MoviesDto saveMovie(MoviesUploadDto moviesUploadDto) {
        Movies movieFoundByName = moviesRepository.findMoviesByMovieName(moviesUploadDto.getMovieName());
        if(Objects.isNull(movieFoundByName)){
            Movies movie = new Movies();
            movie.setMovieName(moviesUploadDto.getMovieName());
            movie.setMovieDescription(moviesUploadDto.getMovieDescription());
            String malformedBase64 = moviesUploadDto.getMoviePicture();
            String repairedBase64 = malformedBase64.substring(malformedBase64.indexOf(',')+1);
            movie.setMoviePicture(Base64.getDecoder().decode(repairedBase64));
            Movies savedMovie = moviesRepository.save(movie);
            return MoviesDto.builder()
                    .movieName(savedMovie.getMovieName())
                    .movieDescription(savedMovie.getMovieDescription())
                    .moviePicture(savedMovie.getMoviePicture())
                    .build();
        }
        return null;
    }

    @Override
    public MoviesDto updateMovie(MoviesUpdateDto moviesUpdateDto) {
        Optional<Movies> moviesOptional = moviesRepository.findById(Long.parseLong(moviesUpdateDto.getMovieId()));
        if(moviesOptional.isPresent()){
            Movies movieToBeUpdated = moviesOptional.get();
            movieToBeUpdated.setMovieName(Objects.isNull(moviesUpdateDto.getNewName()) ? movieToBeUpdated.getMovieName() : moviesUpdateDto.getNewName());
            movieToBeUpdated.setMovieDescription(Objects.isNull(moviesUpdateDto.getNewDescription()) ? movieToBeUpdated.getMovieDescription() : moviesUpdateDto.getNewDescription());
            moviesRepository.save(movieToBeUpdated);
            return MoviesDto.builder()
                    .movieName(movieToBeUpdated.getMovieName())
                    .movieDescription(movieToBeUpdated.getMovieDescription())
                    .moviePicture(movieToBeUpdated.getMoviePicture())
                    .build();
        }
        return null;
    }

    @Override
    public boolean deleteMovie(MoviesDeleteDto moviesDeleteDto) {
        Long id = Long.parseLong(moviesDeleteDto.getMovieId());
        if(moviesRepository.existsById(id)){
            moviesRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<MoviesDto> getAllMovies() {
        List<MoviesDto> moviesDtos = new ArrayList<>();
        List<Movies> moviesList = moviesRepository.findAll();
        moviesList.forEach( movie -> moviesDtos.add(
                MoviesDto.builder()
                .id(movie.getId())
                .movieName(movie.getMovieName())
                .movieDescription(movie.getMovieDescription())
                .moviePicture(movie.getMoviePicture())
                .build()));
        return moviesDtos;
    }
}
