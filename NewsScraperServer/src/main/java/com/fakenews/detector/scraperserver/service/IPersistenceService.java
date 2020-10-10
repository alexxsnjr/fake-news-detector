package com.fakenews.detector.scraperserver.service;

import com.fakenews.detector.scraperserver.pojo.New;
import java.util.List;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */

public interface IPersistenceService {
    public List<New> findAll();
    public New findById(Long id, Integer cantidad);
    public void save(New newparsed);
    public New update(New newParsed, Long id);
    public void delete(Long id);
}
