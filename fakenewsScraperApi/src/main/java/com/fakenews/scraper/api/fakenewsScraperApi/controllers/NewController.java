package com.fakenews.scraper.api.fakenewsScraperApi.controllers;

import com.fakenews.commons.fakenewsCommons.models.entity.*;
import com.fakenews.scraper.api.fakenewsScraperApi.service.INewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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



}
