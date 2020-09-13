package com.fakenews.scraper.fakenewsScraper.scraper;

import java.util.List;

import com.fakenews.scraper.fakenewsScraper.parser.ParserController;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */

@Slf4j
@Component
@Setter
public class CrawlerService {

    @Autowired
    ParserController parserController;

	private List<String>  crawlDomains;
    private  static final String crawlStorageFolder = "./crawlerHtml";;
	private int numberOfCrawlers;
	private String newsRegexFilters;

	public void start() {

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setIncludeBinaryContentInCrawling(false);
        config.setMaxDepthOfCrawling(1);
        config.setMaxPagesToFetch(500);
        config.setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36");
        PageFetcher fetcher = new PageFetcher(config);
        RobotstxtConfig robotsConfig = new RobotstxtConfig();
        robotsConfig.setEnabled(false);
        RobotstxtServer robotsSvr = new RobotstxtServer(robotsConfig, fetcher);

        CrawlController controller = null;
		try {
			controller = new CrawlController(config, fetcher, robotsSvr);
		} catch (Exception e) {
		    log.error("Error creando crawler", e);
		}
        for (String domain : crawlDomains) {
            controller.addSeed(domain);
        }

        HtmlCrawler.configure(crawlDomains, crawlStorageFolder,newsRegexFilters,parserController );
        controller.start(HtmlCrawler.class, numberOfCrawlers);
	}

	
}
