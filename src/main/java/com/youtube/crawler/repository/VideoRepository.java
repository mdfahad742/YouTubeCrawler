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


//    @Query(value = "SELECT * FROM video_info WHERE title LIKE %:title% OR description LIKE %:description%", nativeQuery = true)
//
//    SELECT * FROM video_info WHERE title LIKE '%tea%' OR description LIKE '%tea%' -> Optimization (Elastic Search)
    Page<VideoInfo> findByTitleContainingOrDescriptionContaining(@NonNull final String title,
                                                                 @NonNull final String description,
                                                                 @NonNull Pageable pageable);

}
