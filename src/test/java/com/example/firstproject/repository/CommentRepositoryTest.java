package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest //JPA와 연동 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /*Case 1번 : 4번째 게시글의 모든 댓글 조회 */
        {
            // 1. 입력 데이터 준비
            Long articleId = 4L;

            // 2. 실제 데이터
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 3. 예상 데이터
            Article article = new Article(4L, "영화 머임?????", "기기");
            Comment a = new Comment(1L, article, "1번님", "킹콩 1시간 동안 은 개노젬");
            Comment b = new Comment(2L, article, "2번님", "반지의 제욍 4시간 너무김");
            Comment c = new Comment(3L, article, "3번님", "타겟 영화 돈아까움 ㅇㅈ?");
            List<Comment> exptected = Arrays.asList(a, b, c);
            // 4. 비교 및 검증
            assertEquals(exptected.toString(),comments.toString(),"4번 글의 모든 댓글을 출력!");
        }

    }

    @Test
    void findByNickname() {
    }
}