package com.example.UrlShortener;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<URLModel,Integer> {

    Optional<URLModel> findByShortenUrl(String shortenUrl);

}