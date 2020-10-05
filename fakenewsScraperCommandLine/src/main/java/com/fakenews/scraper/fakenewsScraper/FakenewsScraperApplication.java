package com.fakenews.scraper.fakenewsScraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@RibbonClient(name="news-api")
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})

public class FakenewsScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakenewsScraperApplication.class, args);
    }


}
