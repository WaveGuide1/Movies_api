package io.waveguide.movies.movie;

import io.waveguide.movies.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return null;
    }

    @Override
    public List<MovieRequest> getAllMovies() {
        return List.of();
    }
}
