package spring.postproject.Member.Service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
        assertThat(member).isEqualTo(findMember);
    }

    @DisplayName("회원 탈퇴를 하면 조회가 불가능하다")
    @Test
    public void withdrawTest(){
        //given
        memberService.signUp(member);

        //when
        memberService.withdraw(member,member.getPassword());

        //then
        assertThatThrownBy(()-> memberService.findByNickName(member.getNickname()))
                .isInstanceOf(ExceptionBoard.NOT_FOUND_MEMBER.getException().getClass());
    }

    @DisplayName("로그인이 가능할까")
    @Test
    public void checkUserValidationTest(){

        //given
        memberService.signUp(member);

        //when
        Member validateMember = memberService.checkUserValidation(member.getUserId(),member.getPassword());
        
        //then
        assertThat(member).isEqualTo(validateMember);
    }


    private Member createMember(String userId, String nickName, String passWord){
        return Member.builder()
                .userId(userId)
                .nickName(nickName)
                .password(passWord)
                .build();
    }
}