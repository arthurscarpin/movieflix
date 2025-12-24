package com.github.arthurscarpin.movieflix.mapper;

import com.github.arthurscarpin.movieflix.controller.request.MovieRequest;
import com.github.arthurscarpin.movieflix.controller.response.CategoryResponse;
import com.github.arthurscarpin.movieflix.controller.response.MovieResponse;
import com.github.arthurscarpin.movieflix.controller.response.StreamingResponse;
import com.github.arthurscarpin.movieflix.entity.Category;
import com.github.arthurscarpin.movieflix.entity.Movie;
import com.github.arthurscarpin.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public Movie toMovie(MovieRequest request) {
        List<Category> categories = request.categories()
                .stream()
                .map(categoryId -> Category.builder()
                        .id(categoryId)
                        .build())
                .toList();

        List<Streaming> streaming = request.streaming()
                .stream()
                .map(streamingId -> Streaming.builder()
                        .id(streamingId)
                        .build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streaming(streaming)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie) {
        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        List<StreamingResponse> streaming = movie.getStreaming()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streaming(streaming)
                .build();
    }
}
