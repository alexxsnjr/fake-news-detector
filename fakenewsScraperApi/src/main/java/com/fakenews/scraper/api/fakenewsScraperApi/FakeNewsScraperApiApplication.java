package com.fakenews.scraper.api.fakenewsScraperApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.fakenews.commons.fakenewsCommons.models.dao")
@EntityScan({"com.fakenews.commons.fakenewsCommons.models"})
public class FakeNewsScraperApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeNewsScraperApiApplication.class, args);
	}

}
