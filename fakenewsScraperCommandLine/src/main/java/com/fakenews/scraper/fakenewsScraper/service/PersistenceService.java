package com.fakenews.scraper.fakenewsScraper.service;

import com.fakenews.commons.fakenewsCommons.models.dao.AuthorRepository;
import com.fakenews.commons.fakenewsCommons.models.dao.NewRepository;
import com.fakenews.commons.fakenewsCommons.models.dao.NewspaperRepository;
import com.fakenews.commons.fakenewsCommons.models.entity.Author;
import com.fakenews.commons.fakenewsCommons.models.entity.New;
import com.fakenews.commons.fakenewsCommons.models.entity.Newspaper;
import org.springframework.stereotype.Service;

@Service
public class PersistenceService implements IPersistenceService {

    //TODO:Usar inyeccion de dependencias para usar los repositorios
    private AuthorRepository authorRepository;
    private NewRepository newRepository;
    private NewspaperRepository newspaperRepository;

    private static final PersistenceService INSTANCE = new PersistenceService();

    private PersistenceService() {

    }

    @Override
    public void saveNew(New newParsed) {

        Author author = authorRepository.findByName(newParsed.getAuthor().getName());
        Newspaper newspaper = newspaperRepository.findByName(newParsed.getNewspaper().getName());
        New  newFromDatabase = newRepository.findByUrl(newParsed.getUrl());


        if(newFromDatabase == null){
            New newToPersist = new New();
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

            newRepository.save(newToPersist);
        }

    }

    public static PersistenceService getInstance()
    {
        return INSTANCE;
    }

    public void setAuthorRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void setNewRepository(NewRepository newRepository) {
        this.newRepository = newRepository;
    }

    public void setNewspaperRepository(NewspaperRepository newspaperRepository) {
        this.newspaperRepository = newspaperRepository;
    }
}
