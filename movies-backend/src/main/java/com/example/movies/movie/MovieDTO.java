package com.example.movies.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MovieDTO {

    private String title;

    private String releaseDate;

    private String trailerLink;

    private List<String> genres;
}
