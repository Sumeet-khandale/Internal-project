package com.MovieService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MovieService.Entity.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,String> {
	

}
