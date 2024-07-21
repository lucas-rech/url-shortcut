package com.lucasrech.urlshortcut.repository;

import com.lucasrech.urlshortcut.domain.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {

    public boolean existsByLongUrl(String url);
    public boolean existsByShortUrl(String shortUrl);
    public Url findByShortUrl(String shortcutUrl);

}
