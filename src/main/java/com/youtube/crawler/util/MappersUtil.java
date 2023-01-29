package com.youtube.crawler.util;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.ThumbnailDetails;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.List;


@UtilityClass
public class MappersUtil {
    public static Date toDate(@NonNull final DateTime dateTime) {
        final long epochMilli = dateTime.getValue();
        return new Date(epochMilli);
    }

    public static List<String> toThumbnailUrls(@NonNull final ThumbnailDetails thumbnailDetails) {
        return List.of(
                thumbnailDetails.getDefault().getUrl(),
                thumbnailDetails.getMedium().getUrl(),
                thumbnailDetails.getHigh().getUrl()

        );
    }
}
