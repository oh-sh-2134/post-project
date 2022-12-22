package spring.postproject.Excetion.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.postproject.Excetion.BaseException.BadRequestException;
import spring.postproject.Excetion.BaseException.NotFoundException;
import spring.postproject.Excetion.Dto.ExceptionResponse;

@Slf4j
@org.springframework.web.bind.annotation.ControllerAdvice
public class ExceptionControllerAdvice {

    //400
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String BadRequestExceptionHandler(BadRequestException e, Model model) {
        model.addAttribute("exception",e);
        return "/error";
    }

    //404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException e, Model model) {
        model.addAttribute("exception",e);
        return "/error";
    }

    //500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, Model model) {
        model.addAttribute("exception",e);
        return "/error";
    }

}
