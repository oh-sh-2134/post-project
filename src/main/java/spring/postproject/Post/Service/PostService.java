package spring.postproject.Post.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.postproject.File.Entity.File;
import spring.postproject.File.Repository.FileRepository;
import spring.postproject.File.Service.FileService;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Repository.MemberRepository;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostCreateDto;
import spring.postproject.Post.PostDto.PostUpdateDto;
import spring.postproject.Post.Repository.PostRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {


    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;
    private final FileRepository fileRepository;


//    public Post create(Post post, Long memberId, List<MultipartFile> files) throws IOException {
//        post.addFileList(fileService.convertMultipartFilesFileList(files));
//        post.setMember(memberRepository.findById(memberId).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException));
//        return postRepository.save(post);
//    }

    public Post create(PostCreateDto postCreateDto){
        Post post = postCreateDto.toEntity();
        post.addFileList(fileService.convertMultipartFilesToFileList(postCreateDto.getFiles()));
        post.setMember(memberRepository.findById(postCreateDto.getMemberId()).orElseThrow(ExceptionBoard.NOT_FOUND_MEMBER::getException));
        return postRepository.save(post);
    }

    //더티체킹으로 업데이트 하도록 함
    public Post update(Long id, PostUpdateDto postDto){
        Post post = postRepository.findById(id).orElseThrow(ExceptionBoard.NOT_FOUND_POST::getException);

//        //dir에서 파일 삭제
//        fileService.deleteFiles(post.getFileList());
//        //post에서 엔티티 제거
//        for (File file : post.getFileList()) {
//            post.getFileList().remove(file);
//        }

        post.update(postDto);
        if(!postDto.getFiles().isEmpty()) {
            post.addFileList(fileService.convertMultipartFilesToFileList(postDto.getFiles()));
        }

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
        // post에서 Cascade로 삭제 해줌으로써 해당 post에 있지 않은 fileId가 들어와서 삭제 되는 것을 막아줌
        post.getFileList().remove(file);
        fileService.deleteFileOne(file);


    }


}
