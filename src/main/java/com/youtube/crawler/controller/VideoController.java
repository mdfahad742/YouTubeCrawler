package com.youtube.crawler.controller;


import com.youtube.crawler.model.dto.VideoInfo;
import com.youtube.crawler.model.response.VideoInfoResponse;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.service.VideoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {

    @Autowired
    private final VideoService videoService;

    @GetMapping
    public ResponseEntity<Page<VideoInfoResponse>> getVideos(@NonNull final Pageable pageable) {
        Page<VideoInfo> videoInfos = videoService.getVideos(pageable);
        return null;
    }

    public ResponseEntity<Page<VideoInfoResponse>> searchVideos(@NonNull @RequestParam final VideoSearchRequest searchRequest,
                                                                @NonNull final Pageable pageable) {
        Page<VideoInfo> videoInfos = videoService.searchVideos(searchRequest, pageable);
        return null;
    }

}
