package spring.postproject.Post.Entity;

import lombok.*;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Common.EntityDate;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends EntityDate {

    private final static int MAX_CONTENT_LENGTH = 500;
    private final static int MAX_TITLE_LENGTH = 50;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    public void validContent(String content){
        if(content.length() >MAX_CONTENT_LENGTH || content.isBlank()){
            throw ExceptionBoard.INVALID_CONTENT.getException();
        }
    }

    public void validTitle(String title){
        if (title.length() > MAX_TITLE_LENGTH ||title.isBlank()){
            throw ExceptionBoard.INVALID_TITLE.getException();
        }
    }

    public boolean checkWriter(Member member1,Member member2){
        return member1.isSameMember(member2);
    }

    @Builder
    public Post(String title, String content, Member member) {
        validContent(content);
        validTitle(title);
        this.title = title;
        this.content = content;
        this.member = member;
    }

    //연관관계 메소드
    public void addMember(Member member) {
        this.member = member;
        member.getPostList().add(this);
    }
}
