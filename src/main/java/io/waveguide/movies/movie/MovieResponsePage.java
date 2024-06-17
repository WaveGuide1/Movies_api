package io.waveguide.movies.movie;

import java.util.List;

public record MovieResponsePage(List<MovieRequest> requests,
                                Integer pageNumber, Integer pageSize,
                                long totalElements, int totalPages,
                                boolean isLast) {
    
}
