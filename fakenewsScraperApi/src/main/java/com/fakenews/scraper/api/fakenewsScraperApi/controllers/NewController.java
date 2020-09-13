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

@Slf4j
@RestController
public class NewController {
    @Autowired
    private INewService newService;

    @GetMapping("/list")
    public List<New> getBetweenDate(@RequestParam(name="date_from",required = false)
                                        @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom,
                                    @RequestParam(name="date_to" ,required = false)
                                        @DateTimeFormat(pattern="yyyy-MM-dd") Date dateTo) throws ParseException {

        if (dateFrom == null || dateTo == null)
            return newService.findAll();

        return newService.getBetweenDate(dateFrom,dateTo);
    }

    @GetMapping("/show/{id}")
    public New show(@PathVariable Long id)  {
        New newParsed = newService.findById(id);
        return newParsed;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public New create(@RequestBody New newParsed) {
        log.info(String.format("Petición persistencia --  %s", newParsed.getUrl()) );
        return newService.save(newParsed);
    }

}
