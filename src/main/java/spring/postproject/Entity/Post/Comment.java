package spring.postproject.Entity.Post;

import lombok.*;
import spring.postproject.Entity.Common.EntityDate;
import spring.postproject.Entity.Member.Member;

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
