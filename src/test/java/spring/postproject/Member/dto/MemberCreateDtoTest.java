package spring.postproject.Member.dto;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import javax.validation.constraints.NotBlank;

import static org.junit.Assert.*;

public class MemberCreateDtoTest {

    @DisplayName("닉네임은 필수입니다.")
    @Test
    public void checkNickname(){
        MemberCreateDto memberCreateDto = MemberCreateDto.builder()
                                            .nickname("")
                                            .userId("id")
                                            .password("password")
                                            .build();

    }

    @DisplayName("아이디는 필수입니다.")
    @Test
    public void checkUserId(){

    }
    @DisplayName("비밀번호는 필수입니다.")
    @Test
    public void checkPassword(){

    }

}