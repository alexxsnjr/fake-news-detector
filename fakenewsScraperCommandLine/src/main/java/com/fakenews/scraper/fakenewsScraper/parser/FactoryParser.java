package com.fakenews.scraper.fakenewsScraper.parser;

import com.fakenews.scraper.fakenewsScraper.parser.Newspapers.ElDiarioES;
import com.fakenews.scraper.fakenewsScraper.parser.Newspapers.NewspaperParser;
import com.fakenews.scraper.fakenewsScraper.parser.Newspapers.OkDiarioParser;

public class FactoryParser {
    public static NewspaperParser getNewspaperParser(String url) {

        if(url.contains("okdiario")){
            return new OkDiarioParser();
        }else if(url.contains("eldiario")){
            return new ElDiarioES();
        }

        return null;

    }
}
