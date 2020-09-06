package com.fakenews.scraper.fakenewsScraper.scraper;

import com.fakenews.scraper.fakenewsScraper.parser.ParserController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class HtmlCrawler extends WebCrawler {

    private static Pattern FILTERS;

    private static File storageFolder;
    private static List<String> crawlDomains;
    private HtmlParseData htmlData;

    public static void configure(List<String> crawlDomains, String storageFolderName, String newsRegexFilter) {
        HtmlCrawler.crawlDomains = crawlDomains;
        HtmlCrawler.FILTERS= Pattern.compile(newsRegexFilter);
        storageFolder = new File(storageFolderName);
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
    }

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        
        for (String domain : crawlDomains) {
            if (href.startsWith(domain) && FILTERS.matcher(href).matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
     
        if (page.getParseData() instanceof HtmlParseData) {
            htmlData = (HtmlParseData) page.getParseData();
            ParserController parserController = new ParserController();
            parserController.startParse(htmlData, url);
        }
    }
    
    public HtmlParseData getHtmlData() {
    	return htmlData;
    }



}
