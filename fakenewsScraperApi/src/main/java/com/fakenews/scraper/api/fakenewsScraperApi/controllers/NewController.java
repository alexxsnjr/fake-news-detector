package com.fakenews.scraper.api.fakenewsScraperApi.controllers;

import com.fakenews.commons.fakenewsCommons.models.entity.*;
import com.fakenews.scraper.api.fakenewsScraperApi.service.INewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */
@Slf4j
@RestController
@RequestMapping("news")
public class NewController {

    private final String CURRENT_API_VERSION = "/v0";

    @Autowired
    private INewService newService;

    @GetMapping(CURRENT_API_VERSION+"/list")
    public List<New> getBetweenDate(@RequestParam(name="date_from",required = false)
                                        @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom,
                                    @RequestParam(name="date_to" ,required = false)
                                        @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo) throws ParseException {

        if (dateFrom == null || dateTo == null)
            return newService.findAll();

        return newService.getBetweenDate(dateFrom,dateTo);
    }

    @GetMapping(CURRENT_API_VERSION+"/show/{id}")
    public New show(@PathVariable Long id)  {
        New newParsed = newService.findById(id);
        return newParsed;
    }

    @PostMapping(CURRENT_API_VERSION+"/create")
    @ResponseStatus(HttpStatus.CREATED)
    public New create(@RequestBody New newParsed) {
        log.info(String.format("Petición persistencia --  %s", newParsed.getUrl()) );
        return newService.save(newParsed);
    }

}
