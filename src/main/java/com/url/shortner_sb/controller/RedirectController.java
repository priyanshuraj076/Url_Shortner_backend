package com.url.shortner_sb.controller;

import com.url.shortner_sb.model.UrlMapping;
import com.url.shortner_sb.service.UrlMappingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    public UrlMappingService urlMappingService;
    public RedirectController(UrlMappingService urlMappingService) {
        this.urlMappingService = urlMappingService;
    }
    @GetMapping("/{shortUrl}")
    private ResponseEntity<Void> redirect(@PathVariable String shortUrl){
        UrlMapping urlMapping=urlMappingService.getOriginalUrl(shortUrl);
        if(urlMapping!=null){
            HttpHeaders httpHeaders=new HttpHeaders();
            httpHeaders.add("Location",urlMapping.getOriginalUrl());
            return ResponseEntity.status(302).headers(httpHeaders).build();
        }else {
            return ResponseEntity.noContent().build();
        }
        
    }
}
