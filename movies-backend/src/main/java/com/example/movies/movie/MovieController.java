package com.example.movies.movie;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
@RestController
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll(){
        return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> findById(@PathVariable ObjectId id){
        return new ResponseEntity<>(movieService.findMovieById(id), HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity<Optional<Movie>> findByTitle(@RequestParam String title){
        return new ResponseEntity<>(movieService.findMovieByName(title), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody MovieDTO movieDTO) throws ParseException {
        return new ResponseEntity<>(movieService.save(movieDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Movie> updateMovie(@RequestBody MovieDTO movieDTO, @RequestParam ObjectId objectId) {
        return new ResponseEntity<>(movieService.findMovieById(objectId).map(movie -> {
            movie.setTitle(movie.getTitle());
            movie.setTrailerLink(movie.getTrailerLink());
            List<Genre> updateGenre = new ArrayList<>();
            for (String genre: movieDTO.getGenres()) {
                updateGenre.add(Genre.valueOf(genre));
            }
            try {
                movie.setReleaseDate(new SimpleDateFormat("yyyy-MM-dd").parse(movieDTO.getReleaseDate()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return movieService.save(movie);
        }).orElseThrow(()->new MovieNotFoundException(movieDTO.getTitle())), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(name = "id") ObjectId id){
        if(!movieService.existsById(id)){
            throw new MovieNotFoundException(id.toString());
        }

        movieService.deleteMovieById(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
