package spring.postproject.Member.dto;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import spring.postproject.Member.Entity.MemberRoll;

import javax.validation.constraints.NotBlank;

@Data
public class MemberCreateDto {

    private String nickname;
    private String userId;
    private String password;

}

