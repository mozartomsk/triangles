package ru.mozar.triangle.ws;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author mozar
 * @since 1.0.0
 *
 * This is a Wrapper for any response from server.
 * As I concern, it's better to abstract from http protocol in part response codes.
 */
@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    private enum Status { success, fail }

    private final Status status;
    private final T data;
    private final String errorMessage;

    public static <T> ResponseEntity<Response<T>> success(T data) {
        return new ResponseEntity<>(new Response<>(Status.success, data, null), HttpStatus.OK);
    }

    public static <T> ResponseEntity<Response<T>> fail(String errorMessage) {
        return new ResponseEntity<>(new Response<>(Status.fail, null, errorMessage), HttpStatus.BAD_REQUEST);
    }
}