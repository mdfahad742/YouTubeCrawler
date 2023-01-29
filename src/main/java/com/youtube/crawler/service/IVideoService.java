package com.youtube.crawler.service;

import com.google.api.services.youtube.model.SearchResult;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.model.response.CrawlerResponse;
import com.youtube.crawler.model.response.VideoInfoResponse;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVideoService {

    CrawlerResponse getVideos(@NonNull final Pageable pageable);

    CrawlerResponse searchVideos(@NonNull final VideoSearchRequest request,
                                         @NonNull final Pageable pageable);

}
