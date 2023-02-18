package com.driver;

import org.springframework.stereotype.Repository;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

@Repository
public class MovieRepository {
    HashMap<String,Movie> movies;
    HashMap<String,Director> directors;
    HashMap<String,List<String>> directorMap;
    public MovieRepository(){
        movies = new HashMap<>();
        directors = new HashMap<>();
        directorMap = new HashMap<>();
    }
    public String addMovie(Movie movie){
        if(movies.containsKey(movie.getName())) return "Movie Already Exists";
        movies.put(movie.getName(),movie);
        return "Movie added successfully";
    }
    public String addDirector(Director director){
        if(directors.containsKey(director.getName())) return "Director already exists";
        directors.put(director.getName(),director);
        directorMap.put(director.getName(), new ArrayList<>());
        return "Director added successfully";
    }
    public Movie getMovieByName(String name){
        if(!movies.containsKey(name)) return null;
        else return movies.get(name);
    }
    public Director getDirectorByName(String name){
        if(!directors.containsKey(name)) return null;
        else return directors.get(name);
    }
    public List<String> getAllMovies(){
        return new ArrayList<>(movies.keySet());
    }
    public String deleteDirectorByName(String name){
        if(!directors.containsKey(name)) return "Director not found";
        List<String> allMovies = new ArrayList<>();
        allMovies = directorMap.get(name);
        directors.remove(name);
        for(String x:allMovies){
            if(movies.containsKey(x)) movies.remove(x);
        }
        directorMap.remove(name);
        return "Director and his movies deleted successfully";

    }
    public String deleteAllDirector(){
        for(String name:directors.keySet()){
            List<String> temp = new ArrayList<>();
            temp = directorMap.get(name);
            for(String x:temp){
                if(movies.containsKey(x)) movies.remove(x);
            }
            directorMap.remove(name);
            directors.remove(name);
        }
        return "All directors removed successfully";
    }
    public String addMovieDirectorPair(String movie, String director){
       if(movies.containsKey(movie) && directors.containsKey(director))
           directorMap.get(director).add(movie);
        return "Added";
    }
    public List<String> getMoviesByDirectorName(String director){
        List<String> list = new ArrayList<>();
        if(directorMap.containsKey(director))
            list = directorMap.get(director);
        return list;
    }
}
