package com.fakenews.scraper.api.fakenewsScraperApi.models.dao;

import com.fakenews.scraper.api.fakenewsScraperApi.models.entity.Newspaper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
    public Newspaper findByName(String name);

}
