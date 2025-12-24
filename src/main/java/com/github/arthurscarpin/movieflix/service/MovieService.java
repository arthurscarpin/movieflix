package com.github.arthurscarpin.movieflix.service;

import com.github.arthurscarpin.movieflix.controller.request.MovieRequest;
import com.github.arthurscarpin.movieflix.controller.response.MovieResponse;
import com.github.arthurscarpin.movieflix.entity.Category;
import com.github.arthurscarpin.movieflix.entity.Movie;
import com.github.arthurscarpin.movieflix.entity.Streaming;
import com.github.arthurscarpin.movieflix.mapper.CategoryMapper;
import com.github.arthurscarpin.movieflix.mapper.MovieMapper;
import com.github.arthurscarpin.movieflix.mapper.StreamingMapper;
import com.github.arthurscarpin.movieflix.respository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository repository;

    private final CategoryService categoryService;

    private final StreamingService streamingService;

    public MovieResponse save(MovieRequest request) {
        Movie movie = MovieMapper.toMovie(request);
        movie.setCategories(this.findCategories(movie.getCategories()));
        movie.setStreaming(this.findStreaming(movie.getStreaming()));
        return MovieMapper.toMovieResponse(repository.save(movie));
    }

    private List<Category> findCategories(List<Category> categories) {
        return categories.stream()
                .map(c -> categoryService.findById(c.getId()))
                .flatMap(Optional::stream)
                .map(CategoryMapper::fromCategoryResponseToCategory)
                .toList();
    }

    private List<Streaming> findStreaming(List<Streaming> streaming) {
        return streaming.stream()
                .map(s -> streamingService.findById(s.getId()))
                .flatMap(Optional::stream)
                .map(StreamingMapper::fromStreamingResponseToStreaming)
                .toList();
    }

    public List<MovieResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public Optional<MovieResponse> findById(Long id) {
        return repository.findById(id)
                .map(MovieMapper::toMovieResponse);
    }

    public List<MovieResponse> findByCategory(Long category) {
        return repository.findMovieByCategories_Id(category)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public List<MovieResponse> findByStreaming(Long streaming) {
        return repository.findMovieByStreaming_Id(streaming)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList();
    }

    public MovieResponse updateById(Long id, MovieRequest request) {
        Movie updatedMovie = MovieMapper.toMovie(request);
        updatedMovie.setId(id);
        updatedMovie.setCategories(this.findCategories(updatedMovie.getCategories()));
        updatedMovie.setStreaming(this.findStreaming(updatedMovie.getStreaming()));
        return MovieMapper.toMovieResponse(repository.save(updatedMovie));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
