package spring.postproject.Excetion.BaseException;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final Integer code;

    public CustomException(String message,Integer code) {
        super(message);
        this.code = code;
    }
}
