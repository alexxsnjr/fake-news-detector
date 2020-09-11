package com.fakenews.scraper.fakenewsScraper;

import com.fakenews.commons.fakenewsCommons.models.dao.AuthorRepository;
import com.fakenews.commons.fakenewsCommons.models.dao.NewRepository;
import com.fakenews.commons.fakenewsCommons.models.dao.NewspaperRepository;
import com.fakenews.scraper.fakenewsScraper.scraper.CrawlerService;
import com.fakenews.scraper.fakenewsScraper.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class CommandLineRunner implements ApplicationRunner {

    private List<String> crawlDomains = new ArrayList<String>();
    private Integer threads;
    private String regex;

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private NewspaperRepository newspaperRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        //Validacion parametros
        args.getOptionNames().forEach(optionName -> {
            if (optionName.equals("domain") && !args.getOptionValues(optionName).isEmpty()) {
                crawlDomains.addAll(args.getOptionValues(optionName));
            }
            if (optionName.equals("threads")) {
                threads = Integer.parseInt(args.getOptionValues(optionName).get(0));
            }
            if (optionName.equals("regex")) {
                regex = args.getOptionValues(optionName).get(0);
            }
        });

        if (threads == null)
            threads = 4;

        //TODO:quitar esta mierda y usar inyeccion de dependencias
        PersistenceService persistenceService = PersistenceService.getInstance();
        persistenceService.setAuthorRepository(authorRepository);
        persistenceService.setNewRepository(newRepository);
        persistenceService.setNewspaperRepository(newspaperRepository);

        //TODO:parametrizar regex
        CrawlerService crawler = new CrawlerService(crawlDomains, threads, regex);
        crawler.start();
    }
}
