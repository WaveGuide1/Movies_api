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
@Builder
public class MovieRequest {

    @NotBlank(message = "Movie Title is required")
    private String title;

    @NotBlank(message = "Movie director is required")
    private String director;

    @NotBlank(message = "Studio is required")
    private String studio;

    @NotBlank(message = "Movie cast is required")
    private Set<String> movieCast;

    private String poster;

    @NotBlank(message = "Release year is required")
    private Integer releaseYear;
}
