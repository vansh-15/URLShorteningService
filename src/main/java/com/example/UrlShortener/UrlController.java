package com.example.UrlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService service;

    @GetMapping("/{shortenUrl}")
    public Optional<URLModel> getUrlInfo(@PathVariable String shortenUrl){
        return service.getUrlInfo(shortenUrl);
    }

    @PostMapping
    public String getShortenUrl(@RequestBody URLModel request){
        return service.getShortenUrl(request.getUrl());
    }

    @PutMapping("{shortenUrl}")
    public URLModel updateUrl(@PathVariable String shortenUrl,@RequestBody URLModel request){
        return service.updateUrl(shortenUrl,request.getUrl());
    }

    @DeleteMapping("{shortenUrl}")
    public boolean deleteUrl(@PathVariable String shortenUrl){
        return service.deleteUrl(shortenUrl);
    }

}
