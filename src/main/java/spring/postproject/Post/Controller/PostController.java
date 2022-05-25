package spring.postproject.Post.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Service.MemberService;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;
import spring.postproject.Post.Service.PostService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.websocket.server.PathParam;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {

    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/post")
    public String postAll(Model model){
        model.addAllAttributes(postService.findAll());
        return "/post/posts";
    }

    @GetMapping("/post/new")
    public String createPost(Model model){
        model.addAttribute("postDto",new PostDto());
        return "/post/postCreate";
    }

    @PostMapping("/post/new")
    public String create(@Valid PostDto postDto, @SessionAttribute(name = "login",required = false) Long memberId){
        log.info("title : " +postDto.getTitle() + " content : " +  postDto.getContent());

        if(memberId == null){
            log.info("member session is not valid");
            return "redirect:/";
        }
//        Member member = memberService.findOne(memberId);
//        log.info("member id : " + member.getId() + " member nickname : "+ member.getNickname());
        Post post =  Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();

        postService.create(post, memberId);
        Member member = post.getMember();
        log.info("post new ");

        log.info("post : " + post);
        log.info("member id : " + member.getId() + " member nickname : "+ member.getNickname());
        log.info("post : " + post);
        log.info("post id : " + post.getId() + " post title : "+ post.getTitle() + " post content : "+ post.getContent());

        return "redirect:/";
    }

    @GetMapping("/post/{postId}/detail")
    public String detail(@PathVariable("postId") Long postId, Model model){
        model.addAttribute("post",postService.findOne(postId));
        return "post/postDetail";
    }


    @GetMapping("/post/{postId}/update")
    public String updateForm(@PathVariable("postId") Long postId, Model model){
        Post post = postService.findOne(postId);
        log.info("post update get");
        log.info("post : " + post);
        log.info("post id : " + post.getId() + " post title : " + post.getTitle() + " post content : " + post.getContent());
        PostDto postDto = new PostDto();
        postDto.toDto(post);
        model.addAttribute("postDto",postDto);
        model.addAttribute("postId",postId);
        return "post/postUpdate";
    }

    @PostMapping("/post/{postId}/update")
    public String update(@PathVariable("postId") Long postId, PostDto postDto, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("postDto",postDto);
            model.addAttribute("postId",postId);
            return "post/postUpdate";
        }
        Post post = postService.update(postId, postDto);
        log.info("post : " + post);
        log.info("post id : " + post.getId() + " post title : " + post.getTitle() + " post content : " + post.getContent());
        return "redirect:/";
    }

}
