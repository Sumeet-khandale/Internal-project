package com.Movie_Service;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.MovieService.Entity.Movie;
import com.MovieService.EnumType.MovieStatus;
import com.MovieService.service.impl.MovieServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieServiceImpl movieService;

    @Test
    public void testGetAllMovies() throws Exception {
        Movie movie = new Movie();
        movie.setId(String.valueOf(123L));
        movie.setTitle("Test Movie");
        movie.setLanguage("English");
        movie.setReleaseDate(LocalDate.of(2025, 1, 1));
        movie.setDurationMinutes(120);
        movie.setStatus(MovieStatus.NOW_SHOWING);

        Mockito.when(movieService.getAllMovies()).thenReturn(Collections.singletonList(movie));

        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Movie"))
                .andExpect(jsonPath("$[0].language").value("English"));
    }
}
