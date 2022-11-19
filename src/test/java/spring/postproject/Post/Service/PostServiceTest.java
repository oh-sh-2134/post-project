package spring.postproject.Post.Service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostCreateDto;
import spring.postproject.Post.PostDto.PostInfoDto;
import spring.postproject.Post.PostDto.PostUpdateDto;

import javax.persistence.EntityManager;

import java.io.FileInputStream;
import java.io.IOException;

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


    private MockMultipartFile getMockUploadFile() throws IOException {
        return new MockMultipartFile("file", "file.jpg", "image/jpg", new FileInputStream("C:/Users/user/Desktop/tistory/diary.jpg"));
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

        postService.create(PostCreateDto.builder().title(post.getTitle()).build());

        assertThat(title).isEqualTo(post.getTitle());
        assertThat(content).isEqualTo(post.getContent());
    }

    @Test
    @DisplayName("post 업데이트 테스트")
    public void update() {
        String newTitle = "newTitle";
        String newContent = "newContent";
        PostInfoDto postInfoDto = PostInfoDto.builder()
                                .post(post)
                                .build();

        postService.create(PostCreateDto.builder()
                            .title(post.getTitle())
                            .content(post.getContent())
                            .build());

        postService.update(post.getId(), PostUpdateDto.builder()
                                        .title(newTitle)
                                        .content(newContent)
                                        .build());

        assertThat(newTitle).isEqualTo(post.getTitle());
        assertThat(newContent).isEqualTo(post.getContent());

    }

}