package spring.postproject.Post.PostDto;

import lombok.Builder;
import lombok.Data;
import spring.postproject.Post.Entity.Post;


@Data
public class PostDto {

    private String title;
    private String content;


    public void toDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
