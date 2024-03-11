package com.example.movies.movie;

import com.example.movies.review.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Document(collection = "movie")
public class Movie {

    @Id
    private ObjectId id;

    private String title;

    private Date releaseDate;

    private String trailerLink;

    private List<Genre> genres = new ArrayList<>();

    @DocumentReference
    private List<Review> reviews = new ArrayList<>();

    public Movie(MovieDTO movieDTO) throws ParseException {
        this.title = movieDTO.getTitle();
        for (String genre: movieDTO.getGenres()) {
            this.genres.add(Genre.valueOf(genre));
        }
        this.releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(movieDTO.getReleaseDate());
        this.trailerLink = movieDTO.getTrailerLink();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getId(), movie.getId()) && Objects.equals(getTitle(), movie.getTitle()) && Objects.equals(getReleaseDate(), movie.getReleaseDate()) && Objects.equals(getTrailerLink(), movie.getTrailerLink()) && Objects.equals(getGenres(), movie.getGenres()) && Objects.equals(getReviews(), movie.getReviews());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getReleaseDate(), getTrailerLink(), getGenres(), getReviews());
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", trailerLink='" + trailerLink + '\'' +
                ", genres=" + genres +
                ", reviews=" + reviews +
                '}';
    }
}
