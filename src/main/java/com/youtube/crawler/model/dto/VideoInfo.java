package com.youtube.crawler.model.dto;


import com.youtube.crawler.util.Constant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "video_info", indexes = {
        @Index(name = Constant.IDX_TITLE, columnList = Constant.TITLE),
        @Index(name = Constant.IDX_DESCRIPTION, columnList = Constant.DESCRIPTION)
})
@Getter
@Setter
@EqualsAndHashCode
public class VideoInfo {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Constant.VIDEO_ID, nullable = false)
    private Long videoId;

    @Column(name = Constant.TITLE, nullable = false)
    private String title;

    @Column(name = Constant.DESCRIPTION)
    private String description;

    @Column(name = Constant.PUBLISHED_DATETIME, nullable = false)
    private Date publishedDatetime;

    @ElementCollection
    @CollectionTable(name = Constant.VIDEO_THUMBNAILS, joinColumns = @JoinColumn(name = Constant.VIDEO_ID))
    @Column(name = Constant.THUMBNAIL_URL)
    private List<String> thumbnailUrls;
}
