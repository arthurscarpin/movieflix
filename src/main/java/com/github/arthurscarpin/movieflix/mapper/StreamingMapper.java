package com.github.arthurscarpin.movieflix.mapper;

import com.github.arthurscarpin.movieflix.controller.request.StreamingRequest;
import com.github.arthurscarpin.movieflix.controller.response.StreamingResponse;
import com.github.arthurscarpin.movieflix.entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public static Streaming fromStreamingRequestToStreaming(StreamingRequest request) {
        return Streaming.builder()
                .name(request.name())
                .build();
    }

    public static Streaming fromStreamingResponseToStreaming(StreamingResponse response) {
        return Streaming.builder()
                .id(response.id())
                .name(response.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
