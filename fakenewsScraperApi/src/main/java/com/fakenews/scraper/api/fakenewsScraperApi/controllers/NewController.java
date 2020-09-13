package com.fakenews.scraper.api.fakenewsScraperApi.controllers;

import com.fakenews.commons.fakenewsCommons.models.entity.*;
import com.fakenews.scraper.api.fakenewsScraperApi.service.INewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class NewController {
    @Autowired
    private INewService newService;

    @GetMapping("/list")
    public List<New> list(){
        return newService.findAll()
                .stream()
                .map(newParsed ->{ return newParsed; })
                .collect(Collectors.toList());
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
