package com.fakenews.scraper.fakenewsScraper;
import com.fakenews.scraper.fakenewsScraper.scraper.CrawlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */
@Slf4j
@Component
public class CommandLineRunner implements ApplicationRunner {

    @Autowired
    CrawlerService crawler;

    private List<String> crawlDomains = new ArrayList<String>();
    private Integer threads;
    private String regex;

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

        crawler.setNumberOfCrawlers(threads);
        crawler.setNewsRegexFilters(regex);
        crawler.setCrawlDomains(crawlDomains);
        crawler.start();
    }

}
