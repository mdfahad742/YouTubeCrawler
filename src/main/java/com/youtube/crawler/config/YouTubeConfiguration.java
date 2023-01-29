package com.youtube.crawler.config;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class YouTubeConfiguration {

    @Bean
    YouTube provideYoutubeBean() {
        return new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), request -> {
        }).setApplicationName("youtube-cmdline-search-sample").build();
    }
}
