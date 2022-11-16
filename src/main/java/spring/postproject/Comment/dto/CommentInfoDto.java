package spring.postproject.Comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.postproject.Comment.Entitiy.Comment;

import javax.validation.constraints.NotBlank;

@Data
public class CommentInfoDto {
    private Long id;
    private String content;

    @Builder
    public CommentInfoDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
