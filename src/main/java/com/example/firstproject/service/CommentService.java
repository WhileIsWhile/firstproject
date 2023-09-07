package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDTO;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // DB 에서 가져올 필요 있음

    public List<CommentDTO> comments(Long articleId) {

        //조회 : 댓글 목록
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 변환 : 엔티티 -> DTO 형태로 변환
         List<CommentDTO> commentDTOS = new ArrayList<CommentDTO>();
         for(int i = 0 ; i < comments.size(); i++){
             Comment c = comments.get(i);
             CommentDTO dto = CommentDTO.createCommentDTO(c);
             commentDTOS.add(dto);
         }
        // 반환

        return commentDTOS;
    }
}
