package com.lucasrech.urlshortcut.domain.url;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ShortUrlDTO(@JsonProperty("short_url") String shortUrl ) {
}
