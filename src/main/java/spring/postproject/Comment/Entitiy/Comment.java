package spring.postproject.Comment.Entitiy;

import lombok.*;
import spring.postproject.Common.EntityDate;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Post.Entity.Post;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Comment extends EntityDate {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String content;

    //연관관계 메소드
    public void addMember(Member member){
        this.member = member;
        member.getCommentList().add(this);
    }

    public void addPost(Post post){
        this.post = post;
        post.getCommentList().add(this);
    }
}
