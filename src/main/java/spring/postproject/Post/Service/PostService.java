package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    public Long save(PostDto postDto, Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(ExceptionBoard.NOT_FOUNT_MEMBER::getException);
        Post post = new Post(postDto.getTitle(),postDto.getContent(),member);
        post.setMember(member);
        return post.getId();
    }

    public Long update(Long id,PostDto postDto){
        Post findPost = postRepository.findById(id).orElseThrow();
        findPost.update(postDto);
        return findPost.getId();
    }

    public void delete(Long id, Member member){
        Post post = postRepository.findById(id).orElseThrow();
        if (!member.isAdmin() || !post.checkWriter(member)) {
            return;
        }
        postRepository.delete(post);
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
