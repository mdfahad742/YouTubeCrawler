package com.youtube.crawler.controller;


import com.youtube.crawler.model.response.VideoInfoResponse;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.service.IVideoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    private IVideoService videoService;

    @Autowired
    public VideoController(@NonNull final IVideoService videoServiceImpl) {
        this.videoService = videoServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<VideoInfoResponse>> getVideos(@NonNull final Pageable pageable) {
        List<VideoInfoResponse> videoInfoResponsePage = videoService.getVideos(pageable);
        return ResponseEntity.ok(videoInfoResponsePage);
    }

    public ResponseEntity<List<VideoInfoResponse>> searchVideos(@NonNull @RequestParam final VideoSearchRequest searchRequest,
                                                                @NonNull final Pageable pageable) {
        List<VideoInfoResponse> videoInfoResponsePage = videoService.searchVideos(searchRequest, pageable);
        return ResponseEntity.ok(videoInfoResponsePage);
    }

}
