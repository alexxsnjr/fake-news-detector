package com.fakenews.scraper.fakenewsScraper.service;

import com.fakenews.commons.fakenewsCommons.models.entity.New;

public interface IPersistenceService {
    public void saveNew(New newparsed);
}
