package spring.postproject.Post.Controller;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.postproject.Comment.Entitiy.Comment;
import spring.postproject.Comment.Service.CommentService;
import spring.postproject.Comment.dto.CommentSaveDto;
import spring.postproject.Excetion.ExceptionBoard;
import spring.postproject.Member.Entity.Member;
import spring.postproject.config.Security.Annotation.CurrentMember;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PostRestApiController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public void commentSave(@RequestBody @NotBlank CommentSaveDto commentSaveDto){
        commentService.save(commentSaveDto);
//        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public void commentDelete(@CurrentMember Member member, @PathVariable("postId") Long postId, @PathVariable("commentId")Long commentId){
        Comment comment = commentService.findOne(commentId);
        Member writer = comment.getMember();
        if(!writer.getId().equals(member.getId())){
            throw ExceptionBoard.FORBIDDEN.getException();
//            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        commentService.delete(comment);
//        return new ResponseEntity(HttpStatus.OK);
    }



}
