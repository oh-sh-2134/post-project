package spring.postproject.Member.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
class MemberServiceTest {


    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setup(){
        member = createMember("id","micky","pass");
    }

    @DisplayName("회원가입을 하고 나면 닉네임이 조회 가능하다")
    @Test
    public void findMemberByNicknameTest(){
        //given
        memberService.signUp(member);

        //when
        Member findMember = memberService.findByNickName(member.getNickname());

        //then
        assertThat(findMember.getNickname()).isEqualTo(member.getNickname());
    }

    @DisplayName("회원 탈퇴를 하면 조회가 불가능하다")
    @Test
    public void withdrawTest(){
        //given
        memberService.signUp(member);

        //when
        memberService.withdraw(member,member.getPassword());

        //then
        assertThatThrownBy(()-> memberRepository.findByNickname(member.getNickname()))
                .isInstanceOf(ExceptionBoard.NOT_FOUNT_MEMBER.getException().getClass());
    }


    private Member createMember(String userId, String nickName, String passWord){
        return Member.builder()
                .userId(userId)
                .nickName(nickName)
                .password(passWord)
                .build();
    }
}