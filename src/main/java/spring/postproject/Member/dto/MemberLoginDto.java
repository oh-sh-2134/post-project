package spring.postproject.Member.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.postproject.Member.Entity.Member;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberLoginDto {

    @NotBlank(message = "잘못된 로그인 입니다.")
    private String userId;
    @NotBlank(message = "잘못된 로그인 입니다.")
    private String password;


//    public MemberLoginDto(@NotBlank(message = "잘못된 로그인 입니다.") String id,
//                          @NotBlank(message = "잘못된 로그인 입니다.") String password) {
//        this.id = id;
//        this.password = password;
//    }



    //toMemberEntity로 바꾸지는 않아도 됨 로그인 서비스에서 할일
}
