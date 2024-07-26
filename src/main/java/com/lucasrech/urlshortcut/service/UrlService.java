package com.lucasrech.urlshortcut.service;

import com.lucasrech.urlshortcut.domain.url.Url;
import com.lucasrech.urlshortcut.domain.url.UrlRequest;
import com.lucasrech.urlshortcut.exception.UrlAlreadyExistsException;
import com.lucasrech.urlshortcut.exception.UrlNotFoundedException;
import com.lucasrech.urlshortcut.repository.UrlRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void createNewShortUrl(UrlRequest urlRequest) {
        String shortUrl = generateShortUrl();
        Url newUrl = new Url();

        //Verify if the url have http or https protocol. In case NOT, then we add the protocol before save in database
        if(!urlRequest.url().contains("https://") && !urlRequest.url().contains("http://")) {
            newUrl.setLongUrl("https://" + urlRequest.url());
            newUrl.setShortUrl(shortUrl);
            newUrl.setCreatedAt(LocalDateTime.now());
        } else {
            newUrl.setLongUrl(urlRequest.url());
            newUrl.setShortUrl(shortUrl);
            newUrl.setCreatedAt(LocalDateTime.now());
        }

        try {
            if (!urlRepository.existsByLongUrl(newUrl.getLongUrl())) {
                urlRepository.save(newUrl);
            } else {
                throw new UrlAlreadyExistsException(newUrl.getLongUrl());
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao acessar o repositório de dados: " + e.getMessage(), e);
        }
    }

    public Url getShortUrl(String shortUrl) {
        if(shortUrl.contains("/")) {
            int lastSlashIndex = shortUrl.lastIndexOf("/");
            shortUrl = shortUrl.substring(lastSlashIndex + 1);
        }

        if(urlRepository.existsByShortUrl(shortUrl)) {
            return urlRepository.findByShortUrl(shortUrl);
        } else {
            throw new UrlNotFoundedException();
        }
    }

    public String getOriginalUrl(String shortUrl) {
        if(urlRepository.existsByShortUrl(shortUrl)) {
            Url url = urlRepository.findByShortUrl(shortUrl);
            return url.getLongUrl();
        } else {
            throw new UrlNotFoundedException();
        }

    }

    protected String generateShortUrl() {
        String regex = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        int lenght = random.nextInt(10 - 5) + 5; //Define o limite máximo do link encurtado para 10.

        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            shortUrl.append(regex.charAt(random.nextInt(regex.length())));
        }

        return shortUrl.toString();
    }

}
