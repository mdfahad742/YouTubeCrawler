package com.youtube.crawler.repository;


import com.youtube.crawler.model.dto.VideoInfo;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<VideoInfo, Long> {

    Page<VideoInfo> findAll(@NonNull final Pageable pageable);
    Page<VideoInfo> findByTitleContainingOrDescriptionContaining(@NonNull final String title,
                                                                 @NonNull final String description,
                                                                 @NonNull Pageable pageable);

}
