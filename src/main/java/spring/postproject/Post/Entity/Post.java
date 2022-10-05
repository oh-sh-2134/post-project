package spring.postproject.Post.Entity;

import lombok.*;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Common.EntityDate;
import spring.postproject.File.Entity.File;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Post.PostDto.PostDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends EntityDate {

    private final static int MAX_CONTENT_LENGTH = 500;
    private final static int MAX_TITLE_LENGTH = 50;

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> fileList;

    //연관관계 매핑
    public Long setMember(Member member){
        member.getPostList().add(this);
        this.member = member;
        return member.getId();
    }



    public void validContent(String content){
        if(content.length() >MAX_CONTENT_LENGTH || content.isBlank()){
            throw ExceptionBoard.INVALID_LENGTH.getException();
        }
    }

    public void validTitle(String title){
        if (title.length() > MAX_TITLE_LENGTH ||title.isBlank()){
            throw ExceptionBoard.INVALID_LENGTH.getException();
        }
    }

    public Post update(PostDto postDto) {
        validTitle(postDto.getTitle());
        validContent(postDto.getContent());
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
        return this;
    }

    @Builder
    public Post(String title, String content, List<File> files) {
        validContent(content);
        validTitle(title);
        this.title = title;
        this.content = content;
        this.count = 0;
        this.fileList = new ArrayList<>();
    }

    public void addFileList(List<File> files){
        for (File file : files) {
            file.setPost(this);
        }
        this.fileList = files;
    }

    public Post counting(){
        this.count += 1;
        return this;
    }
}

