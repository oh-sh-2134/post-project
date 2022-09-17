package spring.postproject.Post.Service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    String title = "Title";
    String content = "Content";

    private Post post;
    private Member member;

    private Member getMember() {
        return Member.builder()
                .nickName("nick")
                .userId("id")
                .password("password")
                .build();
    }

    private Post getPost() {
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

    @BeforeEach
    public void setup(){
        member = getMember();
        memberService.signUp(member);
        em.flush();
        post = getPost();
    }

    
    @Test
    @DisplayName("post save 테스트")
    public void create(){

        postService.create(post,member.getId());

        assertThat(title).isEqualTo(post.getTitle());
        assertThat(content).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("post 업데이트 테스트")
    public void update() {
        String newTitle = "newTitle";
        String newContent = "newContent";
        PostDto postDto = PostDto.builder()
                                .title(newTitle)
                                .content(newContent)
                                .build();

        postService.create(post,member.getId());

        postService.update(post.getId(),postDto);

        assertThat(newTitle).isEqualTo(postDto.getTitle());
        assertThat(newContent).isEqualTo(postDto.getContent());

    }

}