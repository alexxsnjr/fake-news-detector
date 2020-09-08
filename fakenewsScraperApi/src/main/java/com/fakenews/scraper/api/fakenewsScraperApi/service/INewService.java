package com.fakenews.scraper.api.fakenewsScraperApi.service;

import com.fakenews.commons.fakenewsCommons.models.entity.New;

import java.util.List;

public interface INewService {
    public List<New> findAll();
    public New findById(Long id);
    public New save(New producto);
    public void deleteById(Long id);
}
