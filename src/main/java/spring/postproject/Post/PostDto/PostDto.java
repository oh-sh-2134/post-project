package spring.postproject.Post.PostDto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.postproject.Post.Entity.Post;

@Data
@NoArgsConstructor
public class PostDto {

    private String title;
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
