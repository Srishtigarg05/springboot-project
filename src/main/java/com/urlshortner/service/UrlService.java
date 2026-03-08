package com.urlshortner.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortner.entity.UrlMapping;
import com.urlshortner.repository.UrlRepository;
import com.urlshortner.util.ShortCodeGenerator;

@Service
public class UrlService {

    @Autowired
    private UrlRepository repository;

    public String createShortUrl(String originalUrl) {

        String shortCode = ShortCodeGenerator.generateCode(6);

        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortCode(shortCode);
        mapping.setCreatedAt(LocalDateTime.now());

        repository.save(mapping);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {

        return repository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found"))
                .getOriginalUrl();
    }
}
