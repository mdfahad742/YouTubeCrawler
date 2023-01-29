package com.youtube.crawler.service;

import com.google.api.services.youtube.model.SearchResult;
import com.youtube.crawler.model.VideoSearchRequest;
import com.youtube.crawler.model.response.VideoInfoResponse;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVideoService {

    List<VideoInfoResponse> getVideos(@NonNull final Pageable pageable);

    List<VideoInfoResponse> searchVideos(@NonNull final VideoSearchRequest request,
                                         @NonNull final Pageable pageable);

}
