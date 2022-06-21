package spring.postproject.Member.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberRoll {
    NORMAL("ROLE_NORMAL"),ADMIN("ROLE_ADMIN");

    private String value;
}
