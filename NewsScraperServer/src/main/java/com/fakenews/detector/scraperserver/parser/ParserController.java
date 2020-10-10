package com.fakenews.detector.scraperserver.parser;

import com.fakenews.detector.scraperserver.service.IPersistenceService;
import com.fakenews.detector.scraperserver.pojo.New;
import com.fakenews.detector.scraperserver.parser.Newspapers.NewspaperParser;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */
@Slf4j
@Component
public class ParserController {
    private FactoryParser factoryPaths;

    @Autowired
    private IPersistenceService presistenceService;

    //todo: gestionar descarga cuando solo tenemos la url
    public void startParse(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            startParse(doc, url);
        } catch (IOException e) {
            log.error("Error al descargar noticia",e);
        }
    }

    public void startParse(HtmlParseData Htmldata, String url) {
        Document doc = Jsoup.parse(Htmldata.getHtml());
        startParse(doc, url);
    }

    /**
     * call the newspaper factory and create a parser according to the newspaper
     * and send the new to the api to persist
     *
     * @param doc - HTML parser to Document
     * @param url -  New url
     */
    public void startParse(Document doc, String url) {

        NewspaperParser newsParser = factoryPaths.getNewspaperParser(url);
        newsParser.setDoc(doc);
        newsParser.setUrl(url);

        New newParsed = null;
        try {
            newParsed = newsParser.start();
            presistenceService.save(newParsed);
        } catch (Exception e) {
            log.info(String.format("Error al parsear --  %s", url) , e);
        }
    }

}