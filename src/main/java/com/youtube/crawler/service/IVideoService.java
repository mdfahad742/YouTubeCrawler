package com.youtube.crawler.service;

import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.model.response.CrawlerResponse;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;


public interface IVideoService {

    CrawlerResponse getVideos(@NonNull final Pageable pageable);

    CrawlerResponse searchVideos(@NonNull final VideoSearchRequest request,
                                         @NonNull final Pageable pageable);

}
