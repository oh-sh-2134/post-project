package spring.postproject.Post.PostDto;

import lombok.Data;
import spring.postproject.Post.Entity.Post;

import javax.validation.constraints.NotBlank;

@Data
public class PostDto {

    @NotBlank(message = "잘못된 제목입니다.")
    private String title;
    @NotBlank(message = "잘못된 내용입니다.")
    private String content;

}
