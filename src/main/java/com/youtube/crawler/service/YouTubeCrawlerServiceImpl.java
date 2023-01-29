package com.youtube.crawler.service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.youtube.crawler.util.Constants;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Log4j2
@Service
public class YouTubeCrawlerServiceImpl implements IYoutubeCrawlerService {

    private YouTube youTube;

    @Autowired
    public YouTubeCrawlerServiceImpl(@NonNull final YouTube youTube) {
        this.youTube = youTube;
    }

    public List<SearchResult> search(@NonNull final String query) {
        try {
            log.info("Searching youtube");
            // Define the API request for retrieving search results.
            final YouTube.Search.List search = configureSearchQuery(query);
            // Call the API and print results.
            final SearchListResponse searchResponse = search.execute();
            final List<SearchResult> searchResultList = searchResponse.getItems();
            log.info("Search Result: {}", searchResultList);

            return searchResultList;
        } catch (final GoogleJsonResponseException ex) {
            final String errorMessage = String.format("There was a service error, errorCode: %d, errorMessage: %s",
                    ex.getDetails().getCode(), ex.getDetails().getMessage());
            log.error(String.format(errorMessage + " stack trace: %s"), ex);
            // TODO:Define custom exception
            throw new RuntimeException(errorMessage);
        } catch (final IOException ex) {
            final String errorMessage = String.format("There was a IOException, errorDetails: %s", ex.getMessage());
            log.error(String.format(errorMessage + " stack trace: {}"), ex);
            // TODO:Define custom exception
            throw new RuntimeException(errorMessage);
        }
    }

    public YouTube.Search.List configureSearchQuery(@NonNull final String query) throws IOException {
        final YouTube.Search.List search = youTube.search().list(Collections.singletonList("snippet"));
        search.setKey(Constants.API_KEY);
        search.setQ(query);
        search.setType(Collections.singletonList("video"));
        search.setOrder("date");
        LocalDateTime localDateTime = LocalDateTime.now();
        search.setPublishedAfter(localDateTime.minusHours(5)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        search.setMaxResults(Constants.NUMBER_OF_VIDEOS_RETURNED);
        return search;
    }
}
