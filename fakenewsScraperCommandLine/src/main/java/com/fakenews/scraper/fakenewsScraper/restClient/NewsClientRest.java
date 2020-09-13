package com.fakenews.scraper.fakenewsScraper.restClient;

import com.fakenews.commons.fakenewsCommons.models.entity.New;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */

@FeignClient("news-api")
public interface NewsClientRest {
    @GetMapping("/list")
    public List<New> list();

    @GetMapping("/show/{id}")
    public New detalle(@PathVariable Long id);

    @PostMapping("/create")
    public New create(@RequestBody New newParsed);

    @PutMapping("/edit/{id}")
    public New update(@RequestBody New newParsed, @PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id);

}
