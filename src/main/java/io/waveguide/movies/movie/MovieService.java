package io.waveguide.movies.movie;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

    MovieRequest addMovie(MovieRequest request, MultipartFile file) throws IOException;

    MovieRequest updateMovie(Long movieId, MovieRequest request, MultipartFile file) throws IOException;

    MovieRequest getMovie(Long movieId);

    List<MovieRequest> getAllMovies();

    MovieResponsePage getMoviesPagination(Integer pageNumber, Integer pageSize);

    MovieResponsePage getMoviesPaginationAndSorting(Integer pageNumber, Integer pageSize, String sortBy, String dir);

    String deleteMovie(Long movieId) throws IOException;
}