package com.example.movies.movie;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
    Optional<Movie> findById(ObjectId id);

    Optional<Movie> findByTitle(String title);

    @Transactional
    void deleteById(ObjectId id);

    boolean existsById(ObjectId id);
}
