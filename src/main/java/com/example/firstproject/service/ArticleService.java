package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //서비스 생성!(해당 클래스를 서비스로 인식하여 스프링 부트에 객체를 생성(등록))
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
       return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleFormDTO articleFormDTO) {
      Article article = articleFormDTO.toEntity();
      if(article.getId() != null){
          return null;
      }
      return articleRepository.save(article);
    }

    public Article update(Long id, ArticleFormDTO articleFormDTO) {
         //1. 수정용 엔티티 생성
        Article article = articleFormDTO.toEntity();

        log.info("id: {}, article:{}",id,article.toString());

        //2. 대상 엔티티를 조회

       Article target  = articleRepository.findById(id).orElse(null);

        //3. 잘못된 엔티티 조회
        if(target == null || id != article.getId()){
            //400 , 잘못된 요청 응답!

            log.info("잘못된 요청 id:{} , ariticle:{}" , id , article.toString());

            return null;
        }

        //4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;


    }

    public Article delete(Long id) {

        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //잘못된 요청 처리
        if(target == null){
            return null;
        }
        //대상 삭제
        articleRepository.delete(target);
        return target;
    }


    @Transactional//해당 메소드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<ArticleFormDTO> articleFormDTOS) {

        // 1.articleFormDTOS 묶음을 entity 로 변환

            List<Article> articleList = articleFormDTOS.stream()
                    .map(articleFormDTO -> articleFormDTO.toEntity())
                    .collect(Collectors.toList());
        
        // 2. entity 묶음 을 DB 에 저장
            articleList.stream()
                    .forEach(article -> articleRepository.save(article));
        
        //강제 예외 발생
            articleRepository.findById(-1L).orElseThrow(
                    () -> new IllegalArgumentException("결제 실패")
            );
        //결과 값 반환

        return articleList;
    }
}
