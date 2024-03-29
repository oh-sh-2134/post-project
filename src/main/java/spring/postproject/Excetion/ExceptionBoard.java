package spring.postproject.Excetion;


import lombok.Getter;
import spring.postproject.Excetion.BaseException.*;


@Getter
public enum ExceptionBoard {



    NOT_FOUND_FILE(new NotFoundException("파일을 찾을 수 없습니다.",400)),
    NOT_FOUND_MEMBER(new NotFoundException("사용자를 찾을 수 없습니다.",400)),
    NOT_FOUND_POST(new NotFoundException("게시글을 찾을 수 없습니다.",400)),
    NOT_FOUND_COMMENT(new NotFoundException("댓글을 찾을 수 없습니다.",400)),
    FORBIDDEN(new BadRequestException("잘못된 요청입니다.",403)),
    INVALID_LENGTH(new BadRequestException("잘못된 길이입니다.",404)),
    INVALID_PASSWORD(new BadRequestException("잘못된 비밀번호 입니다.",404)),
    INVALID_CONTENT(new BadRequestException("잘못된 형식의 컨텐츠입니다.",404)),
    INVALID_TITLE(new BadRequestException("잘못된 형식의 타이틀입니다.",404)),
    INTERNAL_SERVER(new InternalServerException("서버를 찾을 수 없습니다.", 500)),
    FILE_UPLOAD_FAILURE(new FileHandleFailureException("파일을 저장하는데 실패했습니다.",500)),
    FILE_DELETE_FAILURE(new FileHandleFailureException("파일을 삭제하는데 실패했습니다.",500));

    private final CustomException exception;

    ExceptionBoard(CustomException e){
        this.exception = e;
    }
}
