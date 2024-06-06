package io.waveguide.movies.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.waveguide.movies.utils.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieServiceImp movieServiceImp;

    @PostMapping("/")
    public ResponseEntity<GeneralResponse<MovieRequest>> addMovie(
            @RequestPart MultipartFile file, @RequestPart String movieRequest
            ) throws IOException {
        GeneralResponse<MovieRequest> response = new GeneralResponse<>();
        MovieRequest request = convertToMovieRequest(movieRequest);
        var info = movieServiceImp.addMovie(request, file);
        response.setMessage("Successfully created");
        response.setData(info);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<GeneralResponse<MovieRequest>> updateMovie(
            @PathVariable Long movieId, @RequestPart String movieRequest,
            @RequestPart MultipartFile file
    ) throws IOException {
        if (file.isEmpty()) return null;

        GeneralResponse<MovieRequest> response = new GeneralResponse<>();
        MovieRequest request = convertToMovieRequest(movieRequest);
        var info = movieServiceImp.updateMovie(movieId, request, file);
        response.setMessage("Successfully updated");
        response.setData(info);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<GeneralResponse<MovieRequest>> getMovie(@PathVariable Long movieId){
        GeneralResponse<MovieRequest> response = new GeneralResponse<>();
        MovieRequest movie = movieServiceImp.getMovie(movieId);
        response.setMessage("Successful!");
        response.setData(movie);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<GeneralResponse<List<MovieRequest>>> getAllMovies(){
        GeneralResponse<List<MovieRequest>> generalResponse = new GeneralResponse<>();
        List<MovieRequest> movie = movieServiceImp.getAllMovies();
        generalResponse.setMessage("Successful!");
        generalResponse.setData(movie);
        return ResponseEntity.ok(generalResponse);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId) throws IOException {
        return ResponseEntity.ok(movieServiceImp.deleteMovie(movieId));
    }

    private MovieRequest convertToMovieRequest(String movieRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieRequest, MovieRequest.class);
    }
}
