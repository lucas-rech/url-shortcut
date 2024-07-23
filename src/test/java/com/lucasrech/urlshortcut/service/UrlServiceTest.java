package com.lucasrech.urlshortcut.service;

import com.lucasrech.urlshortcut.domain.url.Url;
import com.lucasrech.urlshortcut.repository.UrlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {

    @InjectMocks
    UrlService service;

    @Mock
    UrlRepository repository;

    Url url;

    @BeforeEach
    public void setUp() {
        url = new Url("www.google.com.br", "abcd123");
    }

    @Test
    void deveRetornarUmUrlCurto() {
        when(repository.findByShortUrl("abcd123")).thenReturn(url);

        Url urlToReturn = service.getShortUrl("abcd123");
        assertEquals(url, urlToReturn);
        verify(repository).findByShortUrl("abcd123");
        verifyNoMoreInteractions(repository);
    }
}
