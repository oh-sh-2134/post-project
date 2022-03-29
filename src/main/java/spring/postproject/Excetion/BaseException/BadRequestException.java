package spring.postproject.Excetion.BaseException;

public class BadRequestException extends CustomException{

    public BadRequestException(String message, Integer code) {
        super(message, code);
    }
}
