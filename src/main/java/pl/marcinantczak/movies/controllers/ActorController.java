/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.marcinantczak.movies.controllers;

import java.util.List;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marcinantczak.movies.Database;
import pl.marcinantczak.movies.models.Actor;
import pl.marcinantczak.movies.models.Movie;

/**
 *
 * @author Marcin
 */
@RestController
@RequestMapping("/actor")
public class ActorController {
    
    @Autowired
    Database db;
    
    @GetMapping("")
    public List<Actor> getActors() {
        return db.getActors();
    }
    
    @GetMapping("{id}")
    public Actor getActor(@PathVariable int id) throws NotFoundException {
        return db.getActors().
                stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Actor not found"));                
    }
}
