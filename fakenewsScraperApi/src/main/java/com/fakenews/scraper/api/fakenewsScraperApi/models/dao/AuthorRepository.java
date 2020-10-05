package com.fakenews.scraper.api.fakenewsScraperApi.models.dao;

import com.fakenews.scraper.api.fakenewsScraperApi.models.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Author findByName(String name);
}
