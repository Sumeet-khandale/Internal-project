package com.MovieService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MovieService.Entity.Showtime;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,String> {}
