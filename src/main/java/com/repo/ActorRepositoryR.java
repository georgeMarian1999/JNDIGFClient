package com.repo;

import com.model.Actor;

import java.util.List;

public interface ActorRepositoryR  {
    List<Actor> getAll();
    Actor save(Actor a, int id_movie);
    void deleteById(Integer integer);
    List<Actor> getActorByMovieID(int movieID);
}
