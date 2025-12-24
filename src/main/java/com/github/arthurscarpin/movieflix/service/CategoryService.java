package com.github.arthurscarpin.movieflix.service;

import com.github.arthurscarpin.movieflix.controller.request.CategoryRequest;
import com.github.arthurscarpin.movieflix.controller.response.CategoryResponse;
import com.github.arthurscarpin.movieflix.entity.Category;
import com.github.arthurscarpin.movieflix.mapper.CategoryMapper;
import com.github.arthurscarpin.movieflix.respository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryResponse save(CategoryRequest request) {
        Category savedCategory = repository.save(CategoryMapper.fromCategoryRequestToCategory(request));
        return CategoryMapper.toCategoryResponse(savedCategory);
    }

    public List<CategoryResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public Optional<CategoryResponse> findById(Long id) {
        return repository.findById(id)
                .map(CategoryMapper::toCategoryResponse);
    }

    public CategoryResponse updateById(Long id, CategoryRequest request) {
        Category category = CategoryMapper.fromCategoryRequestToCategory(request);
        category.setId(id);
        return CategoryMapper.toCategoryResponse(repository.save(category));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
