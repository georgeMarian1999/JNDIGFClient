package com.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_movie;

    private String title;
    private int rating;
    private String genre;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "movie")
    private transient List<Actor> actors;

    public Movie(){

    }

    public Movie(int id,String title,int rating,String genre) {
        this.id_movie = id;
        this.title = title;
        this.rating = rating;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id_movie=" + id_movie +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", genre='" + genre + '\'' +
                '}';
    }

    public int getId() {
        return id_movie;
    }

    public int getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id_movie = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void addActor(Actor a) {
        if (a == null) {
            actors = new ArrayList<>();
        }
        actors.add(a);
        setActors(actors);
    }
    public void deleteActor(Actor a) {
        actors.remove(a);
        setActors(actors);
    }

}
