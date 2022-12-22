package spring.postproject.Excetion.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.postproject.Excetion.BaseException.BadRequestException;
import spring.postproject.Excetion.BaseException.NotFoundException;
import spring.postproject.Excetion.Dto.ExceptionResponse;

@Slf4j
//@org.springframework.web.bind.annotation.RestControllerAdvice
public class ExceptionRestControllerAdvice {

    //400
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse BadRequestExceptionHandler(BadRequestException e) {
        return new ExceptionResponse(e.getMessage(), e.getCode());
    }

    //404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException e) {
        return new ExceptionResponse(e.getMessage(), e.getCode());
    }

    //500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleException(Exception e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
