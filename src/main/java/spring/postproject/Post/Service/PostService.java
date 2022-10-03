package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.Common.File.FileHandler;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;
import spring.postproject.config.Security.model.MemberAdaptor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileHandler fileHandler;


    public Post create(Post post, Long memberId, List<MultipartFile> files) throws IOException {
        post.addFileList(fileHandler.convertMultipartFilesFileList(files));
        post.setMember(memberRepository.findById(memberId).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException));
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
