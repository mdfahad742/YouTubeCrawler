package com.youtube.crawler.mapper;

import com.youtube.crawler.model.dto.VideoInfo;
import com.youtube.crawler.model.response.VideoInfoResponse;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface VideoInfoMapper {
    static final VideoInfoMapper INSTANCE = Mappers.getMapper(VideoInfoMapper.class);

    VideoInfoResponse buildVideoInfoResponse(@NonNull final VideoInfo videoInfo);

    List<VideoInfoResponse> buildVideoInfoListResponse(@NonNull final List<VideoInfo> videoInfo);
}
