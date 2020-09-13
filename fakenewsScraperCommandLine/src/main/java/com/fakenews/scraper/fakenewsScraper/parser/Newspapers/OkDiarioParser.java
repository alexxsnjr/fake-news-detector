package com.fakenews.scraper.fakenewsScraper.parser.Newspapers;

import com.fakenews.commons.fakenewsCommons.models.entity.Author;
import com.fakenews.commons.fakenewsCommons.models.entity.New;
import com.fakenews.commons.fakenewsCommons.models.entity.Newspaper;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */
public class OkDiarioParser extends NewspaperParser {

    private static final String AUTHOR_SELECTOR = "li.author-name a";
    private static final String AUTHOR_ATTRIBUTE = "title";
    private static final String NEWSPAPER_SELECTOR = "#okdiario";
    private static final String NEWSPAPER_ATTRIBUTE = "id";
    private static final String BODY_SELECTOR = "div.entry-content p";

    /**
     * Gets the HTML nodes with cssSelector and create the Object New
     * @return Object New
     * @throws Exception if unable to parse any element
     */
    @Override
    public New start() throws Exception{
        Document doc = getDoc();

        New newParsed = new New();
        Author author = new Author();
        Newspaper newspaper = new Newspaper();

        Elements domAuthor = doc.select(AUTHOR_SELECTOR);
        author.setName(domAuthor.attr(AUTHOR_ATTRIBUTE));

        Elements domNewspaper = doc.select(NEWSPAPER_SELECTOR);
        newspaper.setName(domNewspaper.attr(NEWSPAPER_ATTRIBUTE));

        newParsed.setBody(doc.select(BODY_SELECTOR).html());
        newParsed.setAuthor(author);
        newParsed.setUrl(getUrl());
        newParsed.setNewspaper(newspaper);

        return newParsed;
    }
}
