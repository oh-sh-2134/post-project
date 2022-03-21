package spring.postproject.Post.Entity;

import spring.postproject.Member.Entity.Member;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
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
