package com.fakenews.scraper.fakenewsScraper.parser.Newspapers;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import org.jsoup.nodes.Document;

public abstract class NewspaperParser {
    private String url;
    private Document doc;

    public abstract New start() throws Exception;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }
}
