package spring.postproject.Member.dto;

import lombok.Builder;
import lombok.Getter;
import spring.postproject.Comment.dto.CommentInfoDto;
import spring.postproject.Member.Entity.Member;
import spring.postproject.Member.Entity.MemberRoll;
import spring.postproject.Post.PostDto.PostInfoDto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Getter
public class MemberInfoDto {
    private Long id;

    private String userId;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRoll memberRoll;

    private List<PostInfoDto> postInfoDtoList;

    private List<CommentInfoDto> commentInfoDtoList;

    @Builder
    public MemberInfoDto(Member member){
        this.id = member.getId();
        this.userId = member.getUserId();
        this.nickname = member.getUserId();
        this.memberRoll = member.getMemberRoll();
    }

    public void addPostInfoDtoList(PostInfoDto postInfoDto){
        postInfoDtoList.add(postInfoDto);
    }

    public void addCommentInfoDtoList(CommentInfoDto commentInfoDto){
        commentInfoDtoList.add(commentInfoDto);
    }
}
