package com.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinema.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
