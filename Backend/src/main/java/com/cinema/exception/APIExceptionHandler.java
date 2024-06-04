package com.cinema.exception;

import com.cinema.exception.auth.UserDuplicateException;
import com.cinema.exception.auth.WrongCredentialException;
import com.cinema.exception.auth.WrongPasswordException;
import com.cinema.exception.movie.MovieNotFoundException;
import com.cinema.exception.reservation.ReservationNotFoundException;
import com.cinema.exception.reservation.ReservedSeatException;
import com.cinema.exception.reservationStatus.ReservationStatusNotFoundException;
import com.cinema.exception.room.RoomNotFoundException;
import com.cinema.exception.seance.SeanceNotFoundException;
import com.cinema.exception.seat.SeatNotFoundException;
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

    public ResponseEntity<Error> responseEntityExceptionBuilder(HttpStatus httpStatus, String massage) {
        Error error = new Error();

        error.setCode(httpStatus.value());
        error.setMessage(massage);
        error.setErrorTime(new Date());

        return new ResponseEntity<>(error, httpStatus);

    }

    @ExceptionHandler({
            SpeciesNotFoundException.class,
            MovieNotFoundException.class,
            RoomNotFoundException.class,
            SeanceNotFoundException.class,
            ReservationStatusNotFoundException.class,
            SeatNotFoundException.class,
            ReservationNotFoundException.class


    })
    public ResponseEntity<Error> handleNotFoundException(Exception exception) {
        return responseEntityExceptionBuilder(HttpStatus.NOT_FOUND, exception.getMessage());

    }

    @ExceptionHandler({
            SpeciesDuplicateException.class,
            UserDuplicateException.class,
            ReservedSeatException.class

    })
    public ResponseEntity<Error> handleConflictException(Exception exception) {
        return responseEntityExceptionBuilder(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler({WrongPasswordException.class})
    public ResponseEntity<Error> handleBadRequestException(Exception exception) {
        return responseEntityExceptionBuilder(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler({WrongCredentialException.class})
    public ResponseEntity<Error> handleUnauthorizedException(Exception exception) {
        return responseEntityExceptionBuilder(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }


}
