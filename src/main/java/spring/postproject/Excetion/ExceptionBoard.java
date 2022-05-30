package spring.postproject.Excetion;


import lombok.Getter;
import spring.postproject.Excetion.BaseException.BadRequestException;
import spring.postproject.Excetion.BaseException.CustomException;
import spring.postproject.Excetion.BaseException.NotFoundException;


@Getter
public enum ExceptionBoard {




    NOT_FOUND_MEMBER(new NotFoundException("사용자를 찾을 수 없습니다.",600)),
    NOT_FOUND_POST(new NotFoundException("게시글을 찾을 수 없습니다.",600)),
    //길이 오류
    INVALID_LENGTH(new BadRequestException("잘못된 길이입니다.",601)),
    INVALID_PASSWORD(new BadRequestException("잘못된 비밀번호 입니다.",602)),
    INVALID_CONTENT(new BadRequestException("잘못된 형식의 컨텐츠입니다.",603)),
    INVALID_TITLE(new BadRequestException("잘못된 형식의 타이틀입니다.",604));
    private final CustomException exception;


    ExceptionBoard(CustomException e){
        this.exception = e;
    }

}
