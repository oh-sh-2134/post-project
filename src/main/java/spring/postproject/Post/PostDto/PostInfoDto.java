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

@Getter
public class PostInfoDto {

    private Long id;
    private String title;
    private String content;
    private MemberInfoDto memberInfoDto;
    private List<CommentInfoDto> commentInfoDtoList = new LinkedList<>();
    private List<FileInfoDto> fileInfoDtoList = new LinkedList<>();
    private int count;
    private LocalDateTime localDateTime;
    @Builder
    public PostInfoDto(Post post) {
        this.id = post.getId();
        this.memberInfoDto = MemberInfoDto.builder().member(post.getMember()).build();
        this.title = post.getTitle();
        this.content = post.getContent();
        for (Comment comment : post.getCommentList()) {
            this.commentInfoDtoList.add(CommentInfoDto.builder().comment(comment).build());
        }

        for (File file : post.getFileList()) {
            this.fileInfoDtoList.add(FileInfoDto.builder().file(file).build());
        }
        this.count = post.getCount();
        this.localDateTime = post.getModifiedAt();
    }
}
