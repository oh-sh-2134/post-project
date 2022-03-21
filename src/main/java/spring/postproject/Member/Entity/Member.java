package spring.postproject.Member.Entity;

import lombok.*;
import spring.postproject.Post.Entity.Comment;
import spring.postproject.Post.Entity.Post;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, length = 20,unique = true)
    private String username;

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
        this.nickName = nickname;
    }

    public void updatePassWord(String passWord){
        this.passWord = passWord;
    }

    public void updateRole(MemberRoll memberRoll){
        this.memberRoll = memberRoll;
    }


}
