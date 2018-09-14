/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.marcinantczak.movies.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.marcinantczak.movies.Database;
import pl.marcinantczak.movies.models.Actor;
import pl.marcinantczak.movies.models.Movie;

/**
 *
 * @author Marcin
 */
@Controller
@RequestMapping("/utils")
public class UtilsController {
    @Autowired
    Database db;
    
    @PostMapping("/fill")
    public ResponseEntity fill() {
        try {
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
            Movie[] movies = {
                new Movie(1, "Avengers: Infinity War", 8.5, ft.parse("2018-04-23")),
                new Movie(2, "Deadpool 2", 7.8, ft.parse("2018-04-23")),
                new Movie(3, "Spider-Man: Homecoming", 8.3, ft.parse("2018-04-23"))
            };
            Actor[] actors = {
                new Actor(1, "Robert", "Downey", ft.parse("1965-04-04")),
                new Actor(2, "Tom", "Holland", ft.parse("1996-06-01")),
                new Actor(3, "Ryan", "Reynolds", ft.parse("1976-10-23"))
            };
            
            movies[0].addActor(actors[0]);
            movies[0].addActor(actors[1]);
            movies[1].addActor(actors[2]);
            movies[2].addActor(actors[0]);
            movies[2].addActor(actors[1]);
            db.getMovies().addAll(Arrays.asList(movies));
            db.getActors().addAll(Arrays.asList(actors));
        } catch (ParseException ex) {
            Logger.getLogger(UtilsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.ok().build();
    }
}
