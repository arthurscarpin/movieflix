package com.github.arthurscarpin.movieflix.respository;

import com.github.arthurscarpin.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findMovieByCategories_Id(Long category);

    List<Movie> findMovieByStreaming_Id(Long streaming);
}
