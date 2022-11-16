package spring.postproject.Comment.dto;

import lombok.*;
import spring.postproject.Comment.Entitiy.Comment;


@Getter
public class CommentInfoDto {
    private Long id;
    private String content;

    @Builder
    public CommentInfoDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
