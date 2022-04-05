package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.Repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    public Long save(Post post, Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(ExceptionBoard.NOT_FOUNT_MEMBER::getException);
        post.checkWriter(post.getMember(),member);
        postRepository.save(post);
        return post.getId();
    }
}
