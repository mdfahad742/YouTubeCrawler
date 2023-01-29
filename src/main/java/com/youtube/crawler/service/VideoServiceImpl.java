package com.youtube.crawler.service;

import com.google.api.services.youtube.model.SearchResult;
import com.youtube.crawler.mapper.VideoInfoMapper;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.model.dto.VideoInfo;
import com.youtube.crawler.model.response.VideoInfoResponse;
import com.youtube.crawler.repository.VideoRepository;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j2
@Service
@EnableScheduling
public class VideoServiceImpl implements IVideoService {

    private VideoRepository videoRepository;

    private IYoutubeCrawlerService youtubeCrawlerService;

//    @Autowired
//    private VideoInfoMapper videoInfoMapper;

    @Autowired
    public VideoServiceImpl(@NonNull final VideoRepository videoRepository,
                            @NonNull final  IYoutubeCrawlerService youtubeCrawlerService) {
        this.videoRepository = videoRepository;
        this.youtubeCrawlerService = youtubeCrawlerService;
    }

    public List<VideoInfoResponse> getVideos(@NonNull final Pageable pageable) {
        final Page<VideoInfo> videoInfoPage = videoRepository.findAll(pageable);
        final List<VideoInfoResponse> videoInfoResponsePage = VideoInfoMapper.INSTANCE.buildVideoInfoResponseListFromVideoInfoList(
                videoInfoPage.getContent());
        return videoInfoResponsePage;
    }

    public List<VideoInfoResponse> searchVideos(@NonNull final VideoSearchRequest request,
                                              @NonNull final Pageable pageable) {
        final Page<VideoInfo> videoInfoPage = videoRepository.findByTitleContainingOrDescriptionContaining(
                request.getTitle(), request.getDescription(), pageable);
        final List<VideoInfoResponse> videoInfoResponsePage = VideoInfoMapper.INSTANCE.buildVideoInfoResponseListFromVideoInfoList(
                videoInfoPage.getContent());
        return videoInfoResponsePage;
    }

    @Scheduled(initialDelay = 1000, fixedRate = 20000)
    public List<SearchResult> fetchVideos() {
        final String query = "cricket";
        log.info("Videos triggered at: {}", System.currentTimeMillis());
        List<SearchResult> videos = youtubeCrawlerService.search(query);
        log.info("Videos fetched at {}, {}", System.currentTimeMillis(), videos);
        List<VideoInfo> videoInfoList = VideoInfoMapper.INSTANCE.buildVideoInfoListFromSearchResultList(videos);
        videoRepository.saveAll(videoInfoList);
        return videos;
    }


    public void saveAllVideos(@NonNull List<VideoInfo> videoInfoList) {
        try {
            videoRepository.saveAll(videoInfoList);
        } catch (final Exception ex) {
            final String errorMessage = String.format("Save failed due to %s", ex);
            log.error(errorMessage);
            // TODO: Add custom exceptions
            throw new RuntimeException(errorMessage);
        }
    }

}
