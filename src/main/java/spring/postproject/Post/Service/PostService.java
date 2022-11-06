package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.File.Entity.File;
import spring.postproject.File.Repository.FileRepository;
import spring.postproject.File.Service.FileService;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;


    public Post create(Post post, Long memberId, List<MultipartFile> files) throws IOException {
        post.addFileList(fileService.convertMultipartFilesFileList(files));
        post.setMember(memberRepository.findById(memberId).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException));
        return postRepository.save(post);
    }

    //더티체킹으로 업데이트 하도록 함
    public Post update(Long id,PostDto postDto, List<MultipartFile> files) throws IOException{
        Post post = postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);

        //dir에서 파일 삭제
        fileService.deleteFiles(post.getFileList());
        //post에서 엔티티 제거
        for (File file : post.getFileList()) {
            post.getFileList().remove(file);
        }

        post.update(postDto);
        post.addFileList(fileService.convertMultipartFilesFileList(files));
        return post;
    }

    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);
        for (File file : post.getFileList()) {
//            fileRepository.delete(file);
            fileService.deleteFileOne(file);
        }
        postRepository.delete(post);
    }

    public Post findOne(Long id){ return postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException).counting(); }

    @Transactional(readOnly = true)
    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public void deleteFile(Long fileId, Long postId){
        Post post = postRepository.findById(postId).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);
        File file = fileRepository.findById(fileId).orElseThrow(ExceptionBoard.NOT_FOUND_FILE::getException);
//        File findFile = post.getFileList().stream().filter(file -> file.getId().equals(fileId));
        fileService.deleteFileOne(file);
        post.getFileList().remove(file);


    }


}
