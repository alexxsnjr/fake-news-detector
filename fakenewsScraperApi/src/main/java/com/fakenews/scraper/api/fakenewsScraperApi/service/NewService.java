package com.fakenews.scraper.api.fakenewsScraperApi.service;

import com.fakenews.commons.fakenewsCommons.models.dao.NewRepository;
import com.fakenews.commons.fakenewsCommons.models.entity.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewService implements INewService {
    @Autowired
    NewRepository newRepository;

    @Override
    @Transactional(readOnly = true)
    public List<New> findAll() {
        return (List<New>) newRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public New findById(Long id) {
        return newRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public New save(New producto) {
        return newRepository.save(producto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        newRepository.deleteById(id);
    }
}
