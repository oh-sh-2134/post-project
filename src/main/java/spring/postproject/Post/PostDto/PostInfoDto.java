package spring.postproject.Post.PostDto;

import lombok.Builder;
import lombok.Getter;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Comment.dto.CommentInfoDto;
import spring.postproject.File.Dto.FileInfoDto;
import spring.postproject.File.Entity.File;
import spring.postproject.Member.dto.MemberInfoDto;
import spring.postproject.Post.Entity.Post;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostInfoDto {

    private Long id;
    private String title;
    private String content;
    private MemberInfoDto memberInfoDto;
    private List<CommentInfoDto> commentInfoDtoList;
    private List<FileInfoDto> fileInfoDtoList;
    private int count;
    private LocalDateTime localDateTime;
    @Builder
    public PostInfoDto(Post post) {
        this.id = post.getId();
        this.memberInfoDto = MemberInfoDto.builder().member(post.getMember()).build();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.commentInfoDtoList = post.getCommentList().stream()
                                        .map(c -> CommentInfoDto.builder().comment(c).build())
                                        .collect(Collectors.toList());
        this.fileInfoDtoList = post.getFileList().stream()
                                    .map(f -> FileInfoDto.builder().file(f).build())
                                    .collect(Collectors.toList());
        this.count = post.getCount();
        this.localDateTime = post.getModifiedAt();
    }
}
