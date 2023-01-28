package com.youtube.crawler.service;

import com.youtube.crawler.mapper.VideoInfoMapper;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.model.dto.VideoInfo;
import com.youtube.crawler.model.response.VideoInfoResponse;
import com.youtube.crawler.repository.VideoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoServiceImpl implements IVideoService {

    private VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(@NonNull final VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<VideoInfoResponse> getVideos(@NonNull final Pageable pageable) {
        final Page<VideoInfo> videoInfoPage = videoRepository.findAll(pageable);
        final List<VideoInfoResponse> videoInfoResponsePage = VideoInfoMapper.INSTANCE.buildVideoInfoListResponse(
                videoInfoPage.getContent());
        return videoInfoResponsePage;
    }

    public List<VideoInfoResponse> searchVideos(@NonNull final VideoSearchRequest request,
                                              @NonNull final Pageable pageable) {
        final Page<VideoInfo> videoInfoPage = videoRepository.findByTitleContainingOrDescriptionContaining(
                request.getTitle(), request.getDescription(), pageable);
        final List<VideoInfoResponse> videoInfoResponsePage = VideoInfoMapper.INSTANCE.buildVideoInfoListResponse(
                videoInfoPage.getContent());
        return videoInfoResponsePage;
    }

    @Scheduled(fixedDelay = 10000)
    public void fetchVideos() {
//        videoRepository.saveAll(videos);
    }

}
