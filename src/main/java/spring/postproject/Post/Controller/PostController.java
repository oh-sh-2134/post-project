package spring.postproject.Post.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.postproject.Member.Entity.Member;
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
    public String create(@Valid PostDto postDto, @SessionAttribute(name = "login",required = false) Member member, Model model){
        log.info("title : " +postDto.getTitle() + " content : " +  postDto.getContent());

        if(member == null){
            log.info("member session is not valid");
            return "redirect:/";
        }
        log.info("member id : " + member.getId() + " member nickname : "+ member.getNickname());
        Post post =  Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        post.setMember(member);
        log.info("post id : " + post.getId() + " post title : "+ post.getTitle() + " post content : "+ post.getContent() + " post member : "+ post.getMember().getId());

        postService.create(post);


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
        PostDto postDto = new PostDto();
        postDto.toDto(post);
        model.addAttribute("postDto",postDto);
        model.addAttribute("postId",postId);
        return "post/postUpdate";
    }

    @PostMapping("/post/{postId}/update")
    public String update(@PathVariable("postId") Long postId, @PathVariable("postDto") PostDto postDto, BindingResult result){
        if (result.hasErrors()) {
            return "post/postUpdate";
        }
        Post post = postService.findOne(postId);
        post.update(postDto);
        return "redirect:/";
    }

}
