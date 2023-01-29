package com.youtube.crawler.model.dto;


import com.youtube.crawler.util.Constants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "video_info", indexes = {
        @Index(name = Constants.IDX_TITLE, columnList = Constants.TITLE),
        @Index(name = Constants.IDX_DESCRIPTION, columnList = Constants.DESCRIPTION)
})
@Getter
@Setter
@EqualsAndHashCode
public class VideoInfo {

    @javax.persistence.Id
    @Column(name = Constants.VIDEO_ID, nullable = false)
    private String videoId;

    @Column(name = Constants.TITLE, nullable = false)
    private String title;

    @Column(name = Constants.DESCRIPTION)
    private String description;

    @Column(name = Constants.PUBLISHED_DATETIME, nullable = false)
    private Date publishedDatetime;

    @ElementCollection
    @CollectionTable(name = Constants.VIDEO_THUMBNAILS, joinColumns = @JoinColumn(name = Constants.VIDEO_ID))
    @Column(name = Constants.THUMBNAIL_URL)
    private List<String> thumbnailUrls;
}
