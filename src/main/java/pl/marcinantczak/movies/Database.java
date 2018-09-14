/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.marcinantczak.movies;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import pl.marcinantczak.movies.models.Actor;
import pl.marcinantczak.movies.models.Movie;

/**
 *
 * @author Marcin
 */
@Service
public class Database {
    private static Database db;
    private List<Movie> movies;
    private List<Actor> actors;
    
    private Database() {
        movies = new ArrayList<>();
        actors = new ArrayList<>();
    }
    
    public static Database getInstance() {
        if (db == null)
            db = new Database();
        return db;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Actor> getActors() {
        return actors;
    }
    
    
}
