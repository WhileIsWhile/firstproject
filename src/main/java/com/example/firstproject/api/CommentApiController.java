package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDTO;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long articleId){

        // 서비스 에게 위임
        List<CommentDTO>commentDTO = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }

    // 댓글 생성

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDTO>create(@PathVariable Long articleId,
                                            @RequestBody CommentDTO commentDTO){
        //서비스에게 위임
       CommentDTO createdDTO =  commentService.create(articleId,commentDTO);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
    }
    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO>update(@PathVariable Long id,
                                            @RequestBody CommentDTO commentDTO){
        //서비스에게 위임
        CommentDTO updateDTO =  commentService.update(id,commentDTO);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO>delete(@PathVariable Long id){
        //서비스에게 위임
        CommentDTO deleteDTO =  commentService.delete(id);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deleteDTO);
    }


}
