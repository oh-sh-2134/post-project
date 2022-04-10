package spring.postproject.Member.Service;


import org.springframework.stereotype.Service;
import spring.postproject.Member.Entity.Member;

@Service
public interface LoginService {

    Member login(String id, String password);
    void logout();
}
