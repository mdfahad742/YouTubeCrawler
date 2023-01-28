package com.youtube.crawler.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(schema = "video", name = "video_info")
public class VideoInfo {

    private static final String ID = "id";

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";

    private static final String PUBLISHED_DATETIME = "published_datetime";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = PUBLISHED_DATETIME)
    private Date publishedDatetime;
}
