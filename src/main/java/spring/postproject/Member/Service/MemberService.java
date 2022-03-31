package spring.postproject.Member.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public Member signUp(Member member){
        return memberRepository.save(member);
    }

    //닉네임으로 찾기
    public Member findByNickName(String nickName){
        Optional<Member> findMember = memberRepository.findByNickName(nickName);
        Member member = findMember.orElseThrow(ExceptionBoard.NOT_FOUNT_MEMBER::getException);
        return member;

    }

    //로그인
    public Member checkUserValidation(String userId,String password){
        Optional<Member> findMember = memberRepository.findByUserIdAndPassword(userId, password);
        Member member = findMember.orElseThrow(ExceptionBoard.NOT_FOUNT_MEMBER::getException);
        return member;
    }

    //계정 탈퇴
    public void withdraw (Member member, String password){
        if (!member.getPassword().equals(password)){
            throw ExceptionBoard.INVALID_PASSWORD.getException();
        }
        memberRepository.delete(member);
    }
}
