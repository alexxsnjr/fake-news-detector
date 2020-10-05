package com.fakenews.scraper.fakenewsScraper.pojo;


import java.io.Serializable;

public class Author implements Serializable {

    private Long id;

    private String name;

    //@OneToMany( targetEntity=New.class )
    //private List news;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
