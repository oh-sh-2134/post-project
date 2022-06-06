package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.Excetion.BaseException.CustomException;
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

    public Post create(Post post, Member member){
        post.setMember(member);
        return postRepository.save(post);
    }

    //더티체킹으로 업데이트 하도록 함
    public Post update(Long id,PostDto postDto){
        Post post = postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);
        post.update(postDto);
        return post;
    }

    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);
        postRepository.delete(post);
    }

    public Post findOne(Long id){ return postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException).counting(); }

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }
}
