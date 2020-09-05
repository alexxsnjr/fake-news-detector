package com.fakenews.commons.fakenewsCommons.models.dao;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends CrudRepository<New, Long> {
    public New findByUrl(String url);

}
