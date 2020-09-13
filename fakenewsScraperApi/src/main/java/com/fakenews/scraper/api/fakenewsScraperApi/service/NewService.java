package com.fakenews.scraper.api.fakenewsScraperApi.service;

import com.fakenews.commons.fakenewsCommons.models.dao.AuthorRepository;
import com.fakenews.commons.fakenewsCommons.models.dao.NewRepository;
import com.fakenews.commons.fakenewsCommons.models.dao.NewspaperRepository;
import com.fakenews.commons.fakenewsCommons.models.entity.Author;
import com.fakenews.commons.fakenewsCommons.models.entity.New;
import com.fakenews.commons.fakenewsCommons.models.entity.Newspaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class NewService implements INewService {
    @Autowired
    NewRepository newRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    NewspaperRepository newspaperRepository;

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
    public New save(New newParsed) {
        Author author = authorRepository.findByName(newParsed.getAuthor().getName());
        Newspaper newspaper = newspaperRepository.findByName(newParsed.getNewspaper().getName());
        New  newFromDatabase = newRepository.findByUrl(newParsed.getUrl());

        if(newFromDatabase == null){
            log.info(String.format("NOTICIA NUEVA --  %s", newParsed.getUrl()) );
            New newToPersist = new New();
            Date date = new Date();

            newToPersist.setCreateAt(date);
            newToPersist.setUrl(newParsed.getUrl());
            newToPersist.setBody(newParsed.getBody());

            if(author == null){
                authorRepository.save(newParsed.getAuthor());
                newToPersist.setAuthor(newParsed.getAuthor());
            }else {
                newToPersist.setAuthor(author);
            }

            if(newspaper == null){
                newspaperRepository.save(newParsed.getNewspaper());
                newToPersist.setNewspaper(newParsed.getNewspaper());
            }else {
                newToPersist.setNewspaper(newspaper);
            }
            log.info(String.format("PERSISTIENDO --  %s", newParsed.getUrl()));
            return newRepository.save(newToPersist);
        }
        log.info(String.format("NOTICIA YA EN BBDD --  %s", newParsed.getUrl()) );
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        newRepository.deleteById(id);
    }
}
