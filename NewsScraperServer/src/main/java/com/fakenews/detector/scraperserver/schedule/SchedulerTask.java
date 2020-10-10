package com.fakenews.detector.scraperserver.schedule;

import com.fakenews.detector.scraperserver.scraper.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SchedulerTask {

    @Autowired
    CrawlerService crawler;

    //Scrapeo okdiario todos los dias a las 00:00
    @Scheduled(cron = "0 0 * * * *")
    public void OkDiarioCrawling() {
        List<String> crawlDomains = new ArrayList<String>();
        crawlDomains.add("https://okdiario.com");
        crawler.setNumberOfCrawlers(10);
        crawler.setNewsRegexFilters("(^(.*(-[0-9]+)).*$)");
        crawler.setCrawlDomains(crawlDomains);
        crawler.start();
    }
}
