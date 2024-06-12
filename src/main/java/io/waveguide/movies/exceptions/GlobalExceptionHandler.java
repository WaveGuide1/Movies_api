package io.waveguide.movies.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public ProblemDetail handleMovieNotFound(MovieNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(FileExistException.class)
    public ProblemDetail handleFileExist(FileExistException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EmptyFileException.class)
    public ProblemDetail handleEmptyFile(EmptyFileException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NO_CONTENT, ex.getMessage());
    }
}
