package com.urlshortner.controller;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.urlshortner.service.UrlService;

@RestController
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String url) {

        String shortCode = service.createShortUrl(url);

        return "http://localhost:8080/" + shortCode;
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {

        String originalUrl = service.getOriginalUrl(code);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
