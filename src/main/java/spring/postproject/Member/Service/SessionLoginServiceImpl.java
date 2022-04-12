package spring.postproject.Member.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class SessionLoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;

    @Override
    public Member login(String id, String password) {
        return memberRepository.findByUserIdAndPassword(id, password).orElseThrow(ExceptionBoard.NOT_FOUNT_MEMBER::getException);
    }

    @Override
    public void logout() {

    }
}
