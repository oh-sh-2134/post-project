package spring.postproject.Member.dto;


import lombok.Builder;
import lombok.Getter;
import spring.postproject.Member.Entity.MemberRoll;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberCreateDto {

    @NotBlank(message = "닉네임을 입력해 주세요.")
    private String nickname;
    @NotBlank(message = "아이디를 입력해 주세요")
    private String userId;
    @NotBlank(message = "패스워드를 입력해 주세요.")
    private String password;

    private MemberRoll memberRoll;
}

