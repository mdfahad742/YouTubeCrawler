package com.youtube.crawler.mapper;

import com.google.api.services.youtube.model.SearchResult;
import com.youtube.crawler.model.dto.VideoInfo;
import com.youtube.crawler.model.response.VideoInfoResponse;
import com.youtube.crawler.util.MappersUtil;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring", uses = MappersUtil.class)
@Component
public interface VideoInfoMapper {
    static final VideoInfoMapper INSTANCE = Mappers.getMapper(VideoInfoMapper.class);

    @Mapping(source = "publishedDatetime", target = "publishedAt")
    VideoInfoResponse buildVideoInfoResponseFromVideoInfo(@NonNull final VideoInfo videoInfo);

    @Mapping(source = "id.videoId", target = "videoId")
    @Mapping(source = "snippet.title", target = "title")
    @Mapping(source = "snippet.description", target = "description")
    @Mapping(source = "snippet.publishedAt", target = "publishedDatetime")
    @Mapping(source = "snippet.thumbnails", target = "thumbnailUrls")
    VideoInfo buildVideoInfoFromSearchResult(@NonNull final SearchResult result);

    List<VideoInfo> buildVideoInfoListFromSearchResultList(@NonNull final List<SearchResult> searchResultList);

    List<VideoInfoResponse> buildVideoInfoResponseListFromVideoInfoList(@NonNull final List<VideoInfo> videoInfo);
}
