package spring.postproject.Post.PostDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import spring.postproject.Post.Entity.Post;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class PostCreateDto {

    @NotBlank(message = "제목을 다시 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 다시 입력해주세요.")
    private String content;

    private Long memberId;

    private List<MultipartFile> files;


    public void toDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }

    @Builder
    public PostCreateDto(String title, String content) {
        this.title = title;
        this.content = content;

    }
}
