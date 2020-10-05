package com.fakenews.scraper.fakenewsScraper.restClient;

import com.fakenews.scraper.fakenewsScraper.pojo.New;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Sánchez - @AleXxSnJR
 */

@FeignClient(name = "news-api", url = "localhost:8090", configuration = FeignClientConfig.class)
public interface NewsClientRest {

    final String CURRENT_API_VERSION = "v0";

    @GetMapping("/news/"+CURRENT_API_VERSION+"/list")
    public List<New> list();

    @GetMapping("/news/"+CURRENT_API_VERSION+"/show/{id}")
    public New detalle(@PathVariable Long id);

    @PostMapping("/news/"+CURRENT_API_VERSION+"/create")
    public New create(@RequestBody New newParsed);

    @PutMapping("/news/"+CURRENT_API_VERSION+"/edit/{id}")
    public New update(@RequestBody New newParsed, @PathVariable Long id);

    @DeleteMapping("/news/"+CURRENT_API_VERSION+"/delete/{id}")
    public void delete(@PathVariable Long id);

}
