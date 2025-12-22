package com.github.arthurscarpin.movieflix.mapper;

import com.github.arthurscarpin.movieflix.controller.request.CategoryRequest;
import com.github.arthurscarpin.movieflix.controller.response.CategoryResponse;
import com.github.arthurscarpin.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.name())
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
