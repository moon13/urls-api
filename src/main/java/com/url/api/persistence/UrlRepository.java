package com.url.api.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.url.api.model.URL;

public interface UrlRepository extends MongoRepository<URL, String> {
	
}
