package com.lucasrech.urlshortcut.controller;

import com.lucasrech.urlshortcut.domain.url.OriginalUrlResponse;
import com.lucasrech.urlshortcut.domain.url.ShortUrlDTO;
import com.lucasrech.urlshortcut.domain.url.Url;
import com.lucasrech.urlshortcut.domain.url.UrlRequest;
import com.lucasrech.urlshortcut.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/link")
public class UrlController {
    private final UrlService urlService;
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<Url> createNewUrl(@RequestBody UrlRequest url) {
        urlService.createNewShortUrl(url);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/data")
    public ResponseEntity<OriginalUrlResponse> getShortUrl(@RequestBody ShortUrlDTO shortUrl) {
        Url url = urlService.getShortUrl(shortUrl.shortUrl());
        String formatedShortUrl = "localhost:8080/link/" + shortUrl.shortUrl(); //can be changed for env
        OriginalUrlResponse responseUrl = new OriginalUrlResponse(url.getLongUrl(), formatedShortUrl, url.getCreatedAt());

        return ResponseEntity.ok().body(responseUrl);

    }

    @GetMapping("/{shortCut}")
    public void redirectToUrl(@PathVariable String shortCut, HttpServletResponse response) throws IOException {
        String url = urlService.getOriginalUrl(shortCut);
        if(url != null) {
            if(!url.startsWith("https:")) {
                url = "https://" + url;
            }
            response.sendRedirect(url);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
