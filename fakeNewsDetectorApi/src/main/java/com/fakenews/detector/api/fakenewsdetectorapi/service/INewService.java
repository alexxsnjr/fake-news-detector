package com.fakenews.detector.api.fakenewsdetectorapi.service;

import com.fakenews.detector.api.fakenewsdetectorapi.models.entity.New;
import java.util.Date;
import java.util.List;

public interface INewService {
    public List<New> findAll();
    public New findById(Long id);
    public New save(New producto);
    public void deleteById(Long id);
    public List<New> getBetweenDate(Date dateFrom, Date dateTo);
}
