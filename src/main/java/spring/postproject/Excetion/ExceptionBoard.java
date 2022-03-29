package spring.postproject.Excetion;


import lombok.Getter;
import spring.postproject.Excetion.BaseException.BadRequestException;
import spring.postproject.Excetion.BaseException.CustomException;


@Getter
public enum ExceptionBoard {

    
    //길이 오류
    INVALID_LENGTH(new BadRequestException("잘못된 길이입니다.",600));


    private final CustomException exception;


    ExceptionBoard(CustomException e){
        this.exception = e;
    }

}
