package spring.postproject.Member.Repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @DisplayName("save 테스트")
    @Test
    public void save() {
        memberRepository.save(createMember("id","nN","ps"));
    }

    @DisplayName("닉네임으로 회원 찾기")
    @Test
    public void findByNicknameTest() {

        String id = "qwer1234";
        String nickname = "KingHamzzi";
        String password = "psw";

        //given
        Member member = createMember(id, nickname, password);
        memberRepository.save(member);
//        Member findMember = memberRepository.findByNickname(member.getNickname())
//                .orElseThrow(()-> {throw ExceptionBoard.NOT_FOUNT_MEMBER.getException();});

        Member findMember = memberRepository.findByNickname(member.getNickname())
                .orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);

        assertThat(member.getNickname()).isEqualTo(findMember.getNickname());
    }




    private Member createMember(String userId,String nickName, String passWord){
        return Member.builder()
                .userId(userId)
                .nickName(nickName)
                .password(passWord)
                .build();
    }
}