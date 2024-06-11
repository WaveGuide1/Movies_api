package io.waveguide.movies.utils;

import lombok.Data;

@Data
public class GeneralResponse<T> {

    String message;
    T data;
}
