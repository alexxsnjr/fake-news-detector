package com.fakenews.detector.scraperserver.parser;

import com.fakenews.detector.scraperserver.parser.Newspapers.ElDiarioES;
import com.fakenews.detector.scraperserver.parser.Newspapers.NewspaperParser;
import com.fakenews.detector.scraperserver.parser.Newspapers.OkDiarioParser;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */
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
