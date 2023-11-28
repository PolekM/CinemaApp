package com.cinema.exception;

import com.cinema.exception.auth.UserDuplicateException;
import com.cinema.exception.auth.WrongPasswordException;
import com.cinema.exception.movie.MovieNotFoundException;
import com.cinema.exception.room.RoomNotFoundException;
import com.cinema.exception.seance.SeanceNotFoundException;
import com.cinema.exception.species.SpeciesDuplicateException;
import com.cinema.exception.species.SpeciesNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class APIExceptionHandler {

    //TODO - minimize code duplication
    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<Error> handleSpeciesNotFoundException(SpeciesNotFoundException speciesNotFoundException) {

        Error error = new Error();

        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMassage(speciesNotFoundException.getMessage());
        error.setErrorTime(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler({MovieNotFoundException.class})
    public ResponseEntity<Error> handleMovieNotFoundException(MovieNotFoundException movieNotFoundException) {

        Error error = new Error();

        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMassage(movieNotFoundException.getMessage());
        error.setErrorTime(new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SpeciesDuplicateException.class)
    public ResponseEntity<Error> handleSpeciesDuplicateException(SpeciesDuplicateException speciesDuplicateException) {

        Error error = new Error();

        error.setCode(HttpStatus.CONFLICT.value());
        error.setMassage(speciesDuplicateException.getMessage());
        error.setErrorTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Error> handleRoomNotFoundException(RoomNotFoundException roomNotFoundException) {

        Error error = new Error();

        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMassage(roomNotFoundException.getMessage());
        error.setErrorTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SeanceNotFoundException.class)
    public ResponseEntity<Error> handleSeanceNotFoundException(SeanceNotFoundException seanceNotFoundException) {

        Error error = new Error();

        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMassage(seanceNotFoundException.getMessage());
        error.setErrorTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<Error> handleUserDuplicateException(UserDuplicateException userDuplicateException) {

        Error error = new Error();

        error.setCode(HttpStatus.CONFLICT.value());
        error.setMassage(userDuplicateException.getMessage());
        error.setErrorTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Error> handleSeanceNotFoundException(WrongPasswordException wrongPasswordException) {

        Error error = new Error();

        error.setCode(HttpStatus.BAD_REQUEST.value());
        error.setMassage(wrongPasswordException.getMessage());
        error.setErrorTime(new Date());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
