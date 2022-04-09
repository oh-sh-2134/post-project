package spring.postproject.Post.PostDto;

import spring.postproject.Post.Entity.Post;

import javax.validation.constraints.NotBlank;

public class PostDto {

    private final String title;
    private final String content;

    public PostDto(@NotBlank(message = "잘못된 제목입니다.")String title,
                   @NotBlank(message = "잘못된 내용입니다.")String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
