package com.youtube.crawler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.model.dto.VideoInfo;
import com.youtube.crawler.model.response.VideoInfoResponse;
import com.youtube.crawler.repository.VideoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VideoService {

    @Autowired
    private final VideoRepository videoRepository;

//    private static final ObjectMapper mapper = new ObjectMapper();

    public Page<VideoInfo> getVideos(@NonNull final Pageable pageable) {
        final Page<VideoInfo> videoInfos = videoRepository.findAll(pageable);

        return null;

    }

    public Page<VideoInfo> searchVideos(@NonNull final VideoSearchRequest request,
                                              @NonNull final Pageable pageable) {
        final Page<VideoInfo> videoInfos = videoRepository.findByTitleContainingOrDescriptionContaining(
                request.getTitle(), request.getDescription(), pageable);
        return null;
    }

    @Scheduled(fixedDelay = 10000)
    public void fetchVideos() {
//        videoRepository.saveAll(videos);
    }

}
