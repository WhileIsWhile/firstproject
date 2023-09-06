package com.example.firstproject.entity;

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


}
