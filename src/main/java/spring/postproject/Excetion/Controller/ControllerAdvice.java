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
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse BadRequestExceptionHandler(BadRequestException e) {
        return new ExceptionResponse(e.getMessage(), e.getCode());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException e) {
        return new ExceptionResponse(e.getMessage(), e.getCode());
    }

    //@Valid 유효성을 통과하지 못하면 발생하는 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
}
