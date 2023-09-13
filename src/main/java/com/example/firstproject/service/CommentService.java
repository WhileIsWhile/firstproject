package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository; // DB 에서 가져올 필요 있음

    public List<CommentDTO> comments(Long articleId) {

        //조회 : 댓글 목록
        /*List<Comment> comments = commentRepository.findByArticleId(articleId);*/
        // 변환 : 엔티티 -> DTO 형태로 변환
/*         List<CommentDTO> commentDTOS = new ArrayList<CommentDTO>();
         for(int i = 0 ; i < comments.size(); i++){
             Comment c = comments.get(i);
             CommentDTO dto = CommentDTO.createCommentDTO(c);
             commentDTOS.add(dto);
         } => 스프링 문법으로 */
        // 반환

        return commentRepository.findByArticleId(articleId).stream()
                .map(comment -> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDTO create(Long articleId, CommentDTO commentDTO) {
        // 게시글 조회 및 예외 발생
       Article article =  articleRepository.findById(articleId).orElseThrow(() -> new IllegalArgumentException("댓글 생성" +
                "실패! 대상 게시글이 없습니다.!"));
        // 댓글 엔티티 생성
       Comment comment = Comment.createComment(commentDTO,article);
        // 댓글 엔티티를 DB로 저장
      Comment created = commentRepository.save(comment);
        //DTO 변경하여 반환

        return CommentDTO.createCommentDTO(created);
    }

    @Transactional
    public CommentDTO update(Long id, CommentDTO commentDTO) {
        // 댓글 조회 및 예외 발생
         Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다!"));
        //댓글 수정
        target.patch(commentDTO);
        //DB로 갱신
        Comment updated = commentRepository.save(target);
        //댓글 엔티티를 DTO로 변호나 및 반환
        return CommentDTO.createCommentDTO(updated);
    }

    @Transactional
    public CommentDTO delete(Long id) {
        // 댓글조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow( () -> new IllegalArgumentException("" +
                        "댓글 삭제 실패! 대상이 없습니다."));

        //댓글 삭제
        commentRepository.delete(target);
        //섹제 댓글 DTO 로 반환
        return CommentDTO.createCommentDTO(target);
    }
}
