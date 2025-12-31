package com.github.arthurscarpin.movieflix.controller;

import com.github.arthurscarpin.movieflix.controller.request.MovieRequest;
import com.github.arthurscarpin.movieflix.controller.response.MovieResponse;
import com.github.arthurscarpin.movieflix.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService service;

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/search", params = {"category", "!streaming"})
    public ResponseEntity<List<MovieResponse>> getByCategory(@RequestParam Long category) {
        return ResponseEntity.ok(service.findByCategory(category));
    }

    @GetMapping(value = "/search", params = {"streaming", "!category"})
    public ResponseEntity<List<MovieResponse>> getByStreaming(@RequestParam Long streaming) {
        return ResponseEntity.ok(service.findByStreaming(streaming));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateById(@Valid @PathVariable Long id, @RequestBody MovieRequest request) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
