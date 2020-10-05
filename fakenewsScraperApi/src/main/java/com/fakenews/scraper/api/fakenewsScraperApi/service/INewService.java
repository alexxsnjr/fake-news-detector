package com.fakenews.scraper.api.fakenewsScraperApi.service;

import com.fakenews.scraper.api.fakenewsScraperApi.models.entity.New;
import java.util.Date;
import java.util.List;

public interface INewService {
    public List<New> findAll();
    public New findById(Long id);
    public New save(New producto);
    public void deleteById(Long id);
    public List<New> getBetweenDate(Date dateFrom, Date dateTo);
}
