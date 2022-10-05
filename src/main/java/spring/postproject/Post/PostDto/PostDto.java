package spring.postproject.Post.PostDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.postproject.File.Entity.File;
import spring.postproject.Post.Entity.Post;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class PostDto {

    @NotBlank(message = "제목을 다시 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 다시 입력해주세요.")
    private String content;



    public void toDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostDto(String title, String content) {
        this.title = title;
        this.content = content;

    }
}
