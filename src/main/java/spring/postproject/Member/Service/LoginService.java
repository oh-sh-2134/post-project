package spring.postproject.Member.Service;


import org.springframework.stereotype.Service;
import spring.postproject.Member.Entity.Member;


public interface LoginService {

    Member login(String id, String password);
    void logout();
}
