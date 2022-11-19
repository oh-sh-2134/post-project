package spring.postproject.Comment.Service;

import org.junit.Before;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Comment.dto.CommentSaveDto;
import spring.postproject.Excetion.BaseException.CustomException;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostCreateDto;
import spring.postproject.Post.Service.PostService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    PostService postService;

    @Autowired
    MemberService memberService;

    private Member member;
    private Post post;

    @BeforeEach
    void setMemberAndPost(){
        MultipartFile file;
        System.out.println("BeforeEach");
        member = memberService.signUp(createMember("userId","nickName","password"));
        post = postService.create(PostCreateDto.builder().content("comment").title("Title").build());
    }
    @DisplayName("Comment save 테스트")
    @Test
    public void CommentSaveTest(){
        //given
        CommentSaveDto commentSaveDto = CommentSaveDto.builder()
                .memberId(member.getId())
                .content("CommentSaveTest")
                .build();

        //then
        Comment comment = commentService.save(commentSaveDto);



        //when
        assertThat(comment.getId()).isNotNull();
        assertThat(comment.getMember().getId()).isEqualTo(member.getId());
        assertThat(comment.getPost().getId()).isEqualTo(post.getId());
        assertThat(comment.getContent()).isEqualTo("CommentSaveTest");



    }

    @DisplayName("commentId로 comment 하나만 불러온다.")
    @Test
    public void findOneTest1(){
        //given
        CommentSaveDto commentSaveDto = CommentSaveDto.builder()
                .memberId(member.getId())
                .content("CommentSaveTest")
                .build();
        Comment comment = commentService.save(commentSaveDto);

        //then
        Comment findOne = commentService.findOne(comment.getId());

        //when
        assertThat(findOne.getId()).isEqualTo(comment.getId());
    }

    @DisplayName("저장 되어 있지 않은 id를 찾으려하면 에러 발생")
    @Test
    public void findOneTest2(){
        //given
        //then
        CustomException result = assertThrows(ExceptionBoard.NOT_FOUND_COMMENT.getException().getClass(), ()->commentService.findOne(0L));
        //when
        assertThat(result.getMessage()).isEqualTo(ExceptionBoard.NOT_FOUND_COMMENT.getException().getMessage());
    }

    private Member createMember(String userId, String nickName, String password){
        return Member.builder()
                .userId(userId)
                .nickName(nickName)
                .password(password)
                .build();
    }

    private Post createPost(String comment, String Title){
        return Post.builder()
                .content(comment)
                .title(Title)
                .build();
    }
}