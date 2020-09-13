package com.fakenews.scraper.fakenewsScraper.parser.Newspapers;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */
@Getter@Setter
public abstract class NewspaperParser {
    private String url;
    private Document doc;

    /**
     * Gets the HTML nodes with cssSelector and create the Object New
     * @return Object New
     * @throws Exception if unable to parse any element
     */
    public abstract New start() throws Exception;

}
