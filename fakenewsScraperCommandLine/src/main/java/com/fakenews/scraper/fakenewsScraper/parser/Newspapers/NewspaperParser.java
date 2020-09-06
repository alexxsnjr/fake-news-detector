package com.fakenews.scraper.fakenewsScraper.parser.Newspapers;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import lombok.Getter;
import lombok.Setter;
import org.jsoup.nodes.Document;

@Getter@Setter
public abstract class NewspaperParser {
    private String url;
    private Document doc;

    public abstract New start() throws Exception;

}
