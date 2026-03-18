package com.movieapp.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")

public class MovieController {
    @Autowired
    private MovieService movieService;

    // SQL Injection Vulnerability for PoC
    @GetMapping("/search")
    public ResponseEntity<?> searchMovies(@RequestParam String title) {
        // WARNING: Vulnerable to SQL Injection (for PoC only)
        // This code should NOT be used in production!
        try {
            javax.persistence.EntityManager em =
                (javax.persistence.EntityManager) ((org.springframework.beans.factory.BeanFactory) org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext().getAutowireCapableBeanFactory()).getBean(javax.persistence.EntityManager.class);
            String sql = "SELECT * FROM movies WHERE title = '" + title + "'";
            java.util.List<?> result = em.createNativeQuery(sql, Movie.class).getResultList();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @GetMapping({"", "/"})
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.updateMovie(id, movie));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
