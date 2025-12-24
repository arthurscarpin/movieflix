package com.github.arthurscarpin.movieflix.service;

import com.github.arthurscarpin.movieflix.controller.request.StreamingRequest;
import com.github.arthurscarpin.movieflix.controller.response.StreamingResponse;
import com.github.arthurscarpin.movieflix.entity.Streaming;
import com.github.arthurscarpin.movieflix.mapper.StreamingMapper;
import com.github.arthurscarpin.movieflix.respository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository repository;

    public StreamingResponse save(StreamingRequest request) {
        Streaming savedStreaming = repository.save(StreamingMapper.fromStreamingRequestToStreaming(request));
        return StreamingMapper.toStreamingResponse(savedStreaming);
    }

    public List<StreamingResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
    }

    public Optional<StreamingResponse> findById(Long id) {
        return repository.findById(id)
                .map(StreamingMapper::toStreamingResponse);
    }

    public StreamingResponse updateById(Long id, StreamingRequest request) {
        Streaming updatedStreaming = StreamingMapper.fromStreamingRequestToStreaming(request);
        updatedStreaming.setId(id);
        return StreamingMapper.toStreamingResponse(repository.save(updatedStreaming));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
