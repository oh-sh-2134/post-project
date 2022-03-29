package spring.postproject.Member.Entity;

import lombok.*;
import spring.postproject.Common.EntityDate;
import spring.postproject.Post.Entity.Comment;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Excetion.ExceptionBoard;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends EntityDate {

    private static final int MAX_LENGTH_NICKNAME = 20;
    private static final int MAX_LENGTH_PASSWORD = 20;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 20,unique = true)
    private String nickName;

    @Column(nullable = false, length = 20)
    private String passWord;

    @Enumerated(EnumType.STRING)
    private MemberRoll memberRoll;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


    public void updateNickName(String nickname){
        validationNickName(nickname);
        this.nickName = nickname;
    }

    public void updatePassWord(String password){
        validationPassWord(password);
        this.passWord = password;
    }

    public void updateRole(MemberRoll memberRoll){
        this.memberRoll = memberRoll;
    }

    public void validationNickName(String nickName){
        if(nickName.isBlank()|| nickName.length() > MAX_LENGTH_NICKNAME){
            throw ExceptionBoard.INVALID_LENGTH.getException();
        }
    }

    public void validationPassWord(String passWord){
        if(passWord.isBlank()|| passWord.length() > MAX_LENGTH_PASSWORD){
            throw ExceptionBoard.INVALID_LENGTH.getException();
        }
    }

    public boolean isSameMember(Member member) {
        return this.id.equals(member.id);
    }

    public boolean isSameMember(Long id) {
        return this.id.equals(id);
    }


    @Builder
    public Member(String userId, String nickName, String passWord){
        validationNickName(nickName);
        validationPassWord(passWord);
        this.userId = userId;
        this.nickName = nickName;
        this.passWord = passWord;
    }

}
