package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Post create(Post post){
        post.addMember(post.getMember());
        return postRepository.save(post);
    }

    public Post update(Long id,PostDto postDto){
        Post findPost = postRepository.findById(id).orElseThrow();
        findPost.update(postDto);
        return findPost;
    }

    public void delete(Long id, Member member){
        Post post = postRepository.findById(id).orElseThrow();
        if (!member.isAdmin() || !post.checkWriter(member)) {
            return;
        }
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Post findOne(Long id){ return postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException); }

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
