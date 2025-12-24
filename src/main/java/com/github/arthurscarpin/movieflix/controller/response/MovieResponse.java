package com.github.arthurscarpin.movieflix.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieResponse(
        Long id,
        String title,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
        LocalDate releaseDate,
        double rating,
        List<CategoryResponse> categories,
        List<StreamingResponse> streaming
) {
}
