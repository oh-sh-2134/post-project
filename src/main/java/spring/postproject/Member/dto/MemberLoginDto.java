package spring.postproject.Member.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberLoginDto {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userId;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Builder
    public MemberLoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
