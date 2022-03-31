package spring.postproject.Excetion.BaseException;

public class NotFoundException extends CustomException {
    public NotFoundException(String message, Integer code) {
        super(message, code);
    }
}
