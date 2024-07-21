package com.lucasrech.urlshortcut.domain.url;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record OriginalUrlResponse(String url, @JsonProperty("short_url") String shortUrl, @JsonProperty("created_at") LocalDateTime time) {
}
