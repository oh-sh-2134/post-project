package spring.postproject.Excetion.BaseException;

import lombok.Getter;

@Getter
public class FileUploadFailureException extends CustomException{
    public FileUploadFailureException(String message, Integer code) {
        super(message, code);
    }
}
