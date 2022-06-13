package spring.postproject.Member.dto;


import lombok.*;

@Data
@NoArgsConstructor
public class MemberLoginDto {

    private String userId;
    private String password;

    @Builder
    public MemberLoginDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
