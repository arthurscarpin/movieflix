package com.github.arthurscarpin.movieflix.mapper;

import com.github.arthurscarpin.movieflix.controller.request.CategoryRequest;
import com.github.arthurscarpin.movieflix.controller.response.CategoryResponse;
import com.github.arthurscarpin.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category fromCategoryRequestToCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .build();
    }

    public static Category fromCategoryResponseToCategory(CategoryResponse response) {
        return Category.builder()
                .id(response.id())
                .name(response.name())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build(
        );
    }
}
