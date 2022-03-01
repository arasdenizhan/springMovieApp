package com.example.moviedatabase.repository;

import com.example.moviedatabase.model.Movies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movies, Long> {
    Movies findMoviesByMovieName(String name);
}
