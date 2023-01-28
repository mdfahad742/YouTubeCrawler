package com.youtube.crawler.model.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "video", name = "video_info")
@Getter
@Setter
@EqualsAndHashCode
public class VideoInfo {

    private static final String VIDEO_ID = "video_id";

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    private static final String PUBLISHED_DATETIME = "published_datetime";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = VIDEO_ID, nullable = false)
    private Long videoId;

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = PUBLISHED_DATETIME)
    private Date publishedDatetime;

    @ElementCollection
    @CollectionTable(name = "video_thumbnails", joinColumns = @JoinColumn(name = "video_id"))
    @Column(name = "thumbnail_url")
    private List<String> thumbnailUrls;
}
