package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 자동으로 1씩 증가
    private Long id;

    @ManyToOne//해당 댓글의 엔티티가 , 하나의 Article 에 연관된다.
    @JoinColumn(name= "article_id") //(article_id 컬럼에 Article의 대표id 저장)외래키 생성 , Article 엔티티의 기본키(id)와 매핑
    private Article article;    // 해당 댓글에 부모 게시글

    @Column
    private String nickname;    // 댓글을 단사람

    @Column
    private String body;        // 댓글 내용!


    public static Comment createComment(CommentDTO commentDTO, Article article) {

        // 예외처리
        if(commentDTO.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! " +
                    "댓글의 id 가 없어야 합니다.");
        if(commentDTO.getArticleId() != article.getId())
            throw  new IllegalArgumentException("댓글 생성 실패! 게시글의 id 가 잘못 돼었습니다");
        // 엔티티 생성 및 반환
        return new Comment(
                commentDTO.getId(),
                article,
                commentDTO.getNickname(),
                commentDTO.getBody()
        );
    }

    public void patch(CommentDTO commentDTO) {
        //예외 발생
        if(this.id != commentDTO.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id " +
                    "입력 되었습니다");
        //객체를 갱신
        if(commentDTO.getNickname() != null)
            this.nickname = commentDTO.getNickname();
        if(commentDTO.getBody() != null)
            this.body = commentDTO.getBody();
    }
}
