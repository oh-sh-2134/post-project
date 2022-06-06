package spring.postproject.Member.Entity;

import lombok.*;
import spring.postproject.Common.EntityDate;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Excetion.ExceptionBoard;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member extends EntityDate {

    private static final int MAX_LENGTH_NICKNAME = 30;
    private static final int MAX_LENGTH_PASSWORD = 20;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 20,unique = true)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRoll memberRoll;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


    public void updateNickName(String nickname){
        validationNickname(nickname);
        this.nickname = nickname;
    }

    public void updatePassword(String password){
        validationPassword(password);
        this.password = password;
    }

    public void updateRole(MemberRoll memberRoll){
        this.memberRoll = memberRoll;
    }

    public void validationNickname(String nickName){
        if(nickName.isBlank()|| nickName.length() > MAX_LENGTH_NICKNAME){
            throw ExceptionBoard.INVALID_LENGTH.getException();
        }
    }

    public void validationPassword(String password){
        if(password.isBlank()|| password.length() > MAX_LENGTH_PASSWORD){
            throw ExceptionBoard.INVALID_LENGTH.getException();
        }
    }


    @Builder
    public Member(String userId, String nickName, String password){
        validationNickname(nickName);
        validationPassword(password);
        this.userId = userId;
        this.nickname = nickName;
        this.password = password;
    }


}


