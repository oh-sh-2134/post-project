package spring.postproject.Member.dto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberCreateDto {

    private String nickname;
    private String userId;
    private String password;

    @Builder
    public MemberCreateDto(String nickname, String userId, String password) {
        this.nickname = nickname;
        this.userId = userId;
        this.password = password;
    }
}

