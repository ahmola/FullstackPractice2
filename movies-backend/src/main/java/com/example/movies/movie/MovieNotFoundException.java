package com.example.movies.movie;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String title){
        super("There is no such movie : " + title);
    }
}
