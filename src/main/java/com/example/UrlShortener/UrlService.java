package com.example.UrlShortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    @Autowired
    private UrlRepo repo;

    public Optional<URLModel> getUrlInfo(String shortenUrl) {
        return repo.findByShortenUrl(shortenUrl);
    }

    public String getShortenUrl(String originalUrl) {

        URLModel model=new URLModel();
        model.setUrl(originalUrl);
        model.setShortenUrl(generateShortenUrl());
        model.setCreatedAt(LocalDateTime.now());
        model.setUpdatedAt(LocalDateTime.now());

        repo.save(model);
        return model.getShortenUrl();
    }
    public String generateShortenUrl(){
        return UUID.randomUUID().toString()
                .replace("-","")
                .substring(0,6);
    }

    public URLModel updateUrl(String shortenUrl, String newUrl) {
        URLModel model=repo.findByShortenUrl(shortenUrl).orElseThrow(() ->new RuntimeException("not found url"));
        model.setUrl(newUrl);
        model.setUpdatedAt(LocalDateTime.now());
        return repo.save(model);
    }

    public boolean deleteUrl(String shortenUrl) {
        URLModel model=repo.findByShortenUrl(shortenUrl).orElse(null);
        if(model==null){
            return false;
        }
        repo.delete(model);
        return true;
    }
}
