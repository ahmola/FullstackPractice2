package com.example.movies.movie;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public Movie save(Movie movie){
        return movieRepository.insert(movie);
    }

    public Movie save(MovieDTO movieDTO) throws ParseException {
        return movieRepository.insert(new Movie(movieDTO));
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public Optional<Movie> findMovieById(ObjectId id){
        return movieRepository.findById(id);
    }

    public Optional<Movie> findMovieByName(String title){
        return movieRepository.findByTitle(title);
    }

    public boolean existsById(ObjectId id){
        return movieRepository.existsById(id);
    }

    public void deleteMovieById(ObjectId id){
        movieRepository.deleteById(id);
    }

}
