package com.lucasrech.urlshortcut.domain.url;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "url")
    private String longUrl;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(nullable = false, name = "creation_date")
    private LocalDateTime createdAt;

    public Url(String url, String shortUrl) {
        this.longUrl = url;
        this.shortUrl = shortUrl;
        createdAt = LocalDateTime.now();
    }

    public Url() {
    }


}
