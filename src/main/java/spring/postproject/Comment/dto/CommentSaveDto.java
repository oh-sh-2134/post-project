package spring.postproject.Comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDto {
    private Long postId;
    private Long memberId;
    @NotBlank(message = "댓글을 다시입력해주세요.")
    private String content;
}
