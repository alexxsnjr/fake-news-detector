package com.fakenews.commons.fakenewsCommons.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "news")
public class New implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String body;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Newspaper newspaper;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public Author getAuthor() {
        return author;
    }

    public Newspaper getNewspaper() {
        return newspaper;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
