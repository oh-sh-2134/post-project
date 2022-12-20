package spring.postproject.Excetion.BaseException;

import lombok.Getter;

@Getter
public class FileHandleFailureException extends CustomException{
    public FileHandleFailureException(String message, Integer code) {
        super(message, code);
    }
}
