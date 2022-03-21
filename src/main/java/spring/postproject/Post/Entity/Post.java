package spring.postproject.Post.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.postproject.Member.Entity.Member;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> commentList;

    //연관관계 메소드
    public void addMember(Member member){
        this.member = member;
        member.getPostList().add(this);
    }


}
