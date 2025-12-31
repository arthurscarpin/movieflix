package com.github.arthurscarpin.movieflix.controller;

import com.github.arthurscarpin.movieflix.controller.request.StreamingRequest;
import com.github.arthurscarpin.movieflix.controller.response.StreamingResponse;
import com.github.arthurscarpin.movieflix.service.StreamingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService service;

    @PostMapping
    public ResponseEntity<StreamingResponse> postSave(@Valid @RequestBody StreamingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.save(request));
    }

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> updateById(@PathVariable Long id, @Valid @RequestBody StreamingRequest request) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.updateById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id)  {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
