package spring.postproject.Post.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.postproject.Post.Entity.Post;
import spring.postproject.Post.PostDto.PostDto;
import spring.postproject.Post.Repository.PostRepository;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
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
    public String create(@Valid PostDto postDto){
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        postRepository.save(post);
        return "redirect:/";
    }

}
