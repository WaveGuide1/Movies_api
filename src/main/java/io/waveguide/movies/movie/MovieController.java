package io.waveguide.movies.movie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.waveguide.movies.utils.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movies")
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

    private MovieRequest convertToMovieRequest(String movieRequest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieRequest, MovieRequest.class);
    }
}
