package spring.postproject.Post.Controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
public class PostController {

    PostRepository postRepository;

    @GetMapping("/post")
    public String postAll(Model model){
        model.addAllAttributes(postRepository.findAll());
        return "/post/posts";
    }

    @GetMapping("/post/new")
    public String createPost(Model model){
        model.addAttribute(new PostDto());
        return "/post/postCreate";
    }

    @PostMapping("/post/new")
    public String create(@Valid PostDto postDto, @SessionAttribute(name = "login",required = false) Member member){
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        if(member == null){
            log.info("member session is not valid");
            return "redirect:/";
        }

        post.setMember(member);
        postRepository.save(post);
        return "redirect:/";
    }

}
