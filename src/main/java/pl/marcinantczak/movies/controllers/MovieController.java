/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.marcinantczak.movies.controllers;

import java.util.List;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marcinantczak.movies.Database;
import pl.marcinantczak.movies.models.Movie;

/**
 *
 * @author Marcin
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    Database db;

    @RequestMapping("")
    public List<Movie> getMovies() {
        return db.getMovies();
    }

    @GetMapping("{id}")
    public Movie getMovie(@PathVariable int id) throws NotFoundException {
        return db.getMovies().
                stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }

    @DeleteMapping("{id}")
    public void deleteMovie(@PathVariable int id) throws NotFoundException {
        Movie movie = db.getMovies().
                stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        db.getMovies().remove(movie);
    }

    @PostMapping("")
    public void addMovie(@RequestBody @Valid Movie movie) {
        db.getMovies().add(movie);
    }
    
    @PutMapping("{id}")
    public void editMovie(@PathVariable int id, @RequestBody @Valid Movie movie) throws NotFoundException {
        Movie oldMovie = db.getMovies().
                stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Movie not found"));
        int index = db.getMovies().indexOf(oldMovie);
        db.getMovies().set(index, movie);
    }
}

