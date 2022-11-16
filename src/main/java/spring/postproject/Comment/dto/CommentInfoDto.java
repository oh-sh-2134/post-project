package spring.postproject.Comment.dto;

import lombok.*;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Member.dto.MemberInfoDto;


@Getter
public class CommentInfoDto {
    private Long id;
    private String content;
    private MemberInfoDto memberInfoDto;

    @Builder
    public CommentInfoDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.memberInfoDto = MemberInfoDto.builder().member(comment.getMember()).build();
    }
}
