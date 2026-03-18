package com.movieapp.movie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        List<Movie> movies = Arrays.asList(new Movie(), new Movie());
        when(movieRepository.findAll()).thenReturn(movies);
        List<Movie> result = movieService.getAllMovies();
        assertEquals(2, result.size());
    }

    @Test
    void testGetMovieById() {
        Movie movie = new Movie();
        movie.setId(1L);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        Optional<Movie> result = movieService.getMovieById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void testCreateMovie() {
        Movie movie = new Movie();
        movie.setTitle("Test Movie");
        when(movieRepository.save(movie)).thenReturn(movie);
        Movie result = movieService.createMovie(movie);
        assertEquals("Test Movie", result.getTitle());
    }

    @Test
    void testUpdateMovie() {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Old Title");
        Movie updated = new Movie();
        updated.setTitle("New Title");
        updated.setDescription("Desc");
        updated.setDirector("Dir");
        updated.setYear(2025);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any(Movie.class))).thenAnswer(i -> i.getArgument(0));
        Movie result = movieService.updateMovie(1L, updated);
        assertEquals("New Title", result.getTitle());
        assertEquals("Desc", result.getDescription());
        assertEquals("Dir", result.getDirector());
        assertEquals(2025, result.getYear());
    }

    @Test
    void testUpdateMovieNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> movieService.updateMovie(1L, new Movie()));
    }

    @Test
    void testDeleteMovie() {
        doNothing().when(movieRepository).deleteById(1L);
        movieService.deleteMovie(1L);
        verify(movieRepository, times(1)).deleteById(1L);
    }
}
