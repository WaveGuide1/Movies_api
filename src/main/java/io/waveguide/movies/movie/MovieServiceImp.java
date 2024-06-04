package io.waveguide.movies.movie;

import io.waveguide.movies.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImp implements MovieService{

    private final MovieRepository movieRepository;

    private final FileService fileService;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${project.poster}")
    private String path;

    @Override
    public MovieRequest addMovie(MovieRequest request, MultipartFile file) throws IOException {

        // Upload the file
        String uploadFileName = fileService.uploadFile(path, file);

        request.setPoster(uploadFileName);

        // Generating posterUrl
        String posterUrl = baseUrl + "/files/" + uploadFileName;

        var movie = Movie.builder()
                .title(request.getTitle())
                .director(request.getDirector())
                .studio(request.getStudio())
                .movieCast(request.getMovieCast())
                .poster(request.getPoster())
                .releaseYear(request.getReleaseYear())
                .posterUrl(posterUrl)
                .build();
        Movie savedMovie = movieRepository.save(movie);


        MovieRequest movieRequest = new MovieRequest();

        BeanUtils.copyProperties(savedMovie, movieRequest);
        return movieRequest;
    }

    @Override
    public MovieRequest getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Not found"));

        MovieRequest response = new MovieRequest();
        BeanUtils.copyProperties(movie, response);
        return response;
    }

    @Override
    public List<MovieRequest> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieRequest> response = new ArrayList<>();
        for(Movie movie: movies){
            var movieDTO = MovieRequest.builder()
                    .title(movie.getTitle()).director(movie.getDirector())
                    .movieCast(movie.getMovieCast()).studio(movie.getStudio())
                    .poster(movie.getPoster()).posterUrl(movie.getPosterUrl())
                    .releaseYear(movie.getReleaseYear()).build();
            response.add(movieDTO);
        }
        return response;
    }
}
