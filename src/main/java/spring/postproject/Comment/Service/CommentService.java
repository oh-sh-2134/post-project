package spring.postproject.Comment.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Comment.Repository.CommentRepository;
import spring.postproject.Comment.dto.CommentSaveDto;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.Repository.PostRepository;



@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    public Comment save(CommentSaveDto commentSaveDto){
        Post post = postRepository.findById(commentSaveDto.getPostId()).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);
        Member member = memberRepository.findById(commentSaveDto.getMemberId()).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException);

        Comment comment = Comment.builder()
                .post(post)
                .member(member)
                .content(commentSaveDto.getContent())
                .build();
        return commentRepository.save(comment);
    }

    public Comment findOne(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(ExceptionBoard.NOT_FOUND_COMMENT::getException);
    }

    public void delete(Comment comment){
        commentRepository.delete(comment);
    }
}
