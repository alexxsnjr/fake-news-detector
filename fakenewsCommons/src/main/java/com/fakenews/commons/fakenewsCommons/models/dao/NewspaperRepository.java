package com.fakenews.commons.fakenewsCommons.models.dao;

import com.fakenews.commons.fakenewsCommons.models.entity.Newspaper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewspaperRepository extends CrudRepository<Newspaper, Long> {
    public  Newspaper findByName(String name);

}
