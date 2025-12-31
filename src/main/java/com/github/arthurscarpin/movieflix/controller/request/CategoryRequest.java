package com.github.arthurscarpin.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Category name is required.") String name) {
}
