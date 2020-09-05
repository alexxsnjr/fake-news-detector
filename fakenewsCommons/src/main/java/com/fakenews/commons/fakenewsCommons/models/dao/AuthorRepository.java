package com.fakenews.commons.fakenewsCommons.models.dao;

import com.fakenews.commons.fakenewsCommons.models.entity.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    public Author findByName(String name);
}
