package spring.postproject.Excetion;


import lombok.Getter;
import spring.postproject.Excetion.BaseException.BadRequestException;
import spring.postproject.Excetion.BaseException.CustomException;
import spring.postproject.Excetion.BaseException.NotFoundException;


@Getter
public enum ExceptionBoard {



    NOT_FOUNT_MEMBER(new NotFoundException("사용자를 찾을 수 없습니다.",601)),

    //길이 오류
    INVALID_LENGTH(new BadRequestException("잘못된 길이입니다.",600)),
    INVALID_PASSWORD(new BadRequestException("잘못된 비밀번호 입니다.",601));

    private final CustomException exception;


    ExceptionBoard(CustomException e){
        this.exception = e;
    }

}
