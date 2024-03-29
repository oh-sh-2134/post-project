package spring.postproject.Post.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.Comment.Service.CommentService;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostCreateDto;
import spring.postproject.Post.PostDto.PostInfoDto;
import spring.postproject.Post.PostDto.PostUpdateDto;
import spring.postproject.Post.Service.PostService;
import spring.postproject.config.Security.Annotation.CurrentMember;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/post")
    public String postAll(Model model) {
        model.addAllAttributes(postService.findAll());
        return "/post/posts";
    }

    @GetMapping("/post/new")
    public String createPost(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        return "/post/postCreate";
    }

    @PostMapping("/post/new")
//    public String create(PostDto postDto, @SessionAttribute(name = "login",required = false) Member member){
    public String create(@Valid PostCreateDto postCreateDto, BindingResult result, Model model, @CurrentMember Member member , List<MultipartFile> files) throws IOException {
        if(result.hasErrors()){
            return "post/postCreate";
        }
        postCreateDto.setFiles(files);
        postCreateDto.setMemberId(member.getId());
        postService.create(postCreateDto);

        return "redirect:/";
    }

    @GetMapping("/post/{postId}/detail")
    public String detail(@PathVariable("postId") Long postId, Model model) {
        PostInfoDto postInfoDto = PostInfoDto.builder().post(postService.findOne(postId)).build();
        model.addAttribute("postInfoDto", postInfoDto);
        return "post/postDetail";
    }


    @GetMapping("/post/{postId}/update")
    public String updateForm(@PathVariable("postId") Long postId, Model model) {
        Post post = postService.findOne(postId);
        PostUpdateDto postUpdateDto = new PostUpdateDto();
        postUpdateDto.toDto(post);
        model.addAttribute("postUpdateDto", postUpdateDto);
        model.addAttribute("postId", postId);
        model.addAttribute("fileList",post.getFileList());
        return "post/postUpdate";
    }

    @PostMapping("/post/{postId}/update")
    public String update(@PathVariable("postId") Long postId, @Valid PostUpdateDto postUpdateDto, BindingResult result,  Model model,  List<MultipartFile> files) throws IOException {
        if(result.hasErrors()){
            model.addAttribute("postId",postId);
            return "post/postUpdate";
        }
        postUpdateDto.setFiles(files);
        postService.update(postId, postUpdateDto);
        return "redirect:/";
    }

    @GetMapping("/post/{postId}/delete")
    public String delete(@PathVariable("postId") Long postId) {
        postService.delete(postId);
        return "redirect:/";
    }

    @PostMapping("/deleteFile")
    public String boardFileDelete(@RequestParam Long fileId, @RequestParam Long postId){
        postService.deleteFile(fileId, postId);
        //게시판 파일삭제
        return "redirect:/post/" + postId  + "/update";
    }
}
