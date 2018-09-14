/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.marcinantczak.movies.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcin
 */
public class Movie {
    @NotNull
    @Id
    private int id;
    @NotNull
    private String title;
    @NotNull
    private double rating;
    @NotNull
    private Date releaseDate;
    @ManyToMany
    @JsonManagedReference
    private List<Actor> actors;

    public Movie() {
        actors = new ArrayList<>();
    }
    
    public Movie(int id, String title, double rating, Date releaseDate) {
        this();
        this.id = id;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public void addActor(Actor a) {
        if (!actors.contains(a)) {
            actors.add(a);
            a.addMovie(this);
        }
    }

    public List<Actor> getActors() {
        return actors;
    }
    
    
    
}
