package com.fakenews.scraper.fakenewsScraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableSpringConfigured
//@Configuration
//@ComponentScan({"com.fakenews.scraper.fakenewsScraper.parser","com.fakenews.scraper.fakenewsScraper.scraper"})
@EnableJpaRepositories("com.fakenews.commons.fakenewsCommons.models.dao")
@EntityScan({"com.fakenews.commons.fakenewsCommons.models"})
public class FakenewsScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakenewsScraperApplication.class, args);
    }


}
