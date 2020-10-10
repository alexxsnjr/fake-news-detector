package com.fakenews.detector.api.fakenewsdetectorapi.models.dao;

import com.fakenews.detector.api.fakenewsdetectorapi.models.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Author findByName(String name);
}
