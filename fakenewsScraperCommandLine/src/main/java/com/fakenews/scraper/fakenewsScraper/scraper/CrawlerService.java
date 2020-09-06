package com.fakenews.scraper.fakenewsScraper.scraper;

import java.util.List;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CrawlerService {
	private List<String>  crawlDomains;
    private String crawlStorageFolder;
	private int numberOfCrawlers;
	private String newsRegexFilters;

	public CrawlerService(List<String> crawlDomains, Integer numberOfCrawlers, String newsRegexFilters) {
		this.crawlDomains = crawlDomains;
        crawlStorageFolder = "./crawlerHtml";
        this.numberOfCrawlers = numberOfCrawlers;
        this.newsRegexFilters=newsRegexFilters;
	}


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

        HtmlCrawler.configure(crawlDomains, crawlStorageFolder,newsRegexFilters );
        controller.start(HtmlCrawler.class, numberOfCrawlers);
	}

	
}
