package spring.postproject.Member.Entity;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.postproject.Excetion.ExceptionBoard;


@DisplayName("Member 클래스")
public class MemberTest {


    private Member member;

    @BeforeEach
    void setUp(){
        member = createMember("niceId","kingHamzzi","password");
    }

    @DisplayName("Member 객체생성이 빌더 패턴으로 생성된다.")
    @Test
    public void constructor(){
        assertThat(member).isNotNull();
        assertThat(member.getUserId()).isEqualTo("niceId");
        assertThat(member.getNickname()).isEqualTo("kingHamzzi");
        assertThat(member.getPassword()).isEqualTo("password");
    }

    @DisplayName("닉네임이 변경가능 해야한다")
    @Test
    public void canChangeNickName() {
        String newNickName = "HamzziKing";
        member.updateNickName(newNickName);
        assertThat(member.getNickname()).isEqualTo(newNickName);
    }

    @DisplayName("비밀번호가 변경가능 해야한다")
    @Test
    public void canChangePassWord() {
        String newPassWord = "newPassWord";
        member.updatePassword(newPassWord);
        assertThat(member.getPassword()).isEqualTo(newPassWord);
    }

    @DisplayName("등급이 변경가능해야한다")
    @Test
    public void canChangeRoll() {
        member.updateRole(MemberRoll.ROLE_ADMIN);
        assertThat(member.getMemberRoll()).isEqualTo(MemberRoll.ROLE_ADMIN);
        member.updateRole(MemberRoll.ROLE_NORMAL);
        assertThat(member.getMemberRoll()).isEqualTo(MemberRoll.ROLE_NORMAL);
    }

    /* check in security
    @DisplayName("비밀번호가 비어있으면 에러가 난다")
    @Test
    public void validationPassWordBlank() {
        //비밀번호 변경시 에러
        assertThatThrownBy(() -> member.updatePassword(""))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Member 생성시 에러
        assertThatThrownBy(() -> createMember("id", "name", ""))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    @DisplayName("비밀번호 길이가 20자리 이상이면 에러가 난다")
    @Test
    public void validationPassWordLengthOver() {
        //비밀번호 변경시 에러
        assertThatThrownBy(() -> member.updatePassword("1234567891011121314151617181920"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Member 생성시 에러
        assertThatThrownBy(() -> createMember("id", "name", "1234567891012345678910"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }
    */

    @DisplayName("닉네임이 비어있으면 에러가 난다")
    @Test
    public void validationNickNameBlank() {
        //닉네임 변경시 에러
        assertThatThrownBy(() -> member.updateNickName(""))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Member 생성시 에러
        assertThatThrownBy(() -> createMember("id", "", "password"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    @DisplayName("닉네임의 길이가 20자리 이상이면 에러가 난다")
    @Test
    public void validationNickNameLengthOver() {
        //닉네임 변경시 에러
        assertThatThrownBy(() -> member.updateNickName("123456789101112131564654as65e4151617181920"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());

        //Member 생성시 에러
        assertThatThrownBy(() -> createMember("id", "123456789101112131564654as65e4151617181920", "password"))
                .isInstanceOf(ExceptionBoard.INVALID_LENGTH.getException().getClass());
    }

    private Member createMember(String userId,String nickName, String passWord){
        return Member.builder()
                .userId(userId)
                .nickName(nickName)
                .password(passWord)
                .build();
    }
}
