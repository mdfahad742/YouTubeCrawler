package com.youtube.crawler.repository;


import com.youtube.crawler.model.dto.VideoInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<VideoInfo, Long> {

    Page<VideoInfo> findAll(Pageable pageable);
    Page<VideoInfo> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageable);

}
