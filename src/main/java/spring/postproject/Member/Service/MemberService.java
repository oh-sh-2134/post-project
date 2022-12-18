package spring.postproject.Member.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Transactional
    public Member signUp(Member member){
        member.updatePassword(bCryptPasswordEncoder.encode(member.getPassword()));
        return memberRepository.save(member);
    }

    //닉네임으로 찾기
    public Member findByNickName(String nickName){
        Optional<Member> findMember = memberRepository.findByNickname(nickName);
        return findMember.orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);
    }

    //로그인
    public Member checkUserValidation(String userId,String password){
        Optional<Member> findMember = memberRepository.findByUserIdAndPassword(userId, password);
        return findMember.orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);
    }

    //계정 탈퇴
    @Transactional
    public void withdraw (Member member, String password){
        if (!member.getPassword().equals(password)){
            throw ExceptionBoard.INVALID_PASSWORD.getException();
        }
        memberRepository.delete(member);
    }

    public Member findOne(Long id){
        return memberRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);
    }

    public Boolean userIdDuplicationCheck(String userId) {
        return memberRepository.existsByUserId(userId);
    }


    public Boolean nicknameDuplicationCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }
}
