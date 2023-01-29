package com.youtube.crawler.service;

import com.google.api.services.youtube.model.SearchResult;
import lombok.NonNull;

import java.util.List;

public interface IYoutubeCrawlerService {
    List<SearchResult> search(@NonNull final String query);
}
