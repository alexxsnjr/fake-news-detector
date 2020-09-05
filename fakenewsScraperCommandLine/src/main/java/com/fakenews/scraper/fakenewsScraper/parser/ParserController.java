package com.fakenews.scraper.fakenewsScraper.parser;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import com.fakenews.scraper.fakenewsScraper.parser.Newspapers.NewspaperParser;
import com.fakenews.scraper.fakenewsScraper.service.PersistenceService;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParserController {
    private FactoryParser factoryPaths;

    //todo: gestionar descarga cuando solo tenemos la url
    public void startParse(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            startParse(doc, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startParse(HtmlParseData Htmldata, String url) {
        Document doc = Jsoup.parse(Htmldata.getHtml());
        startParse(doc, url);
    }

    public void startParse(Document doc, String url) {

        NewspaperParser newsParser = factoryPaths.getNewspaperParser(url);
        newsParser.setDoc(doc);
        newsParser.setUrl(url);
        New newParsed = newsParser.start();

        PersistenceService persistenceService = PersistenceService.getInstance();
        persistenceService.saveNew(newParsed);

    }

}