package com.repo;

import com.model.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> getAll();
    Movie save(Movie m);
    void deleteById(Integer integer);
    Movie findById(int id);
}
