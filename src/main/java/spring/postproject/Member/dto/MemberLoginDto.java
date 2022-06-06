package spring.postproject.Member.dto;


import lombok.*;
import spring.postproject.Member.Entity.Member;

import javax.validation.constraints.NotBlank;

@Data
public class MemberLoginDto {

    private String userId;
    private String password;
}
