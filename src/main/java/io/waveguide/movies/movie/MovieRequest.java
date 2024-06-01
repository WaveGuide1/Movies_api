package io.waveguide.movies.movie;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    @NotBlank(message = "Movie Title is required")
    private String title;

    @NotBlank(message = "Movie director is required")
    private String director;

    @NotBlank(message = "Studio is required")
    private String studio;

    private Set<String> movieCast;

    @NotBlank(message = "Poster is required")
    private String poster;

    @NotBlank(message = "Poster url is required")
    private String posterUrl;

    private Integer releaseYear;
}
