package com.lucasrech.urlshortcut.service;

import com.lucasrech.urlshortcut.domain.url.Url;
import com.lucasrech.urlshortcut.domain.url.UrlRequest;
import com.lucasrech.urlshortcut.exception.UrlAlreadyExistsException;
import com.lucasrech.urlshortcut.exception.UrlNotFoundedException;
import com.lucasrech.urlshortcut.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public void createNewShortUrl(UrlRequest urlRequest) {
        try {
            if(!urlRepository.existsByLongUrl(urlRequest.url())) {
                String shortUrl = generateShortUrl();
                Url newUrl = new Url(urlRequest.url(), shortUrl);
                urlRepository.save(newUrl);
            } else {
                throw new UrlAlreadyExistsException(urlRequest.url());
            }
        } catch (Exception e) {
            throw  new RuntimeException("Unexpected error.", e);
        }
    }

    public Url getShortUrl(String shortUrl) {
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
