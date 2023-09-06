package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest //해당 클래시는 스프링 부트와 연동되어 테스팅 된다.
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {

        //예상
        Article a = new Article(1L,"111111","111111");
        Article b = new Article(2L,"211111","111111");
        Article c = new Article(3L, "311111", "111111");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        //실제
        List<Article> articles = articleService.index();


        //비교
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_success() {
        //존재 하는 id 입력
        Long id = 1L;
        //예상
        Article expected = new Article(id, "111111","111111");
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail() {
        //존재 하지 않는 id
        Long id = -1L;
        //예상 데이터
        Article expected = null;
        //실제 데이터
        Article article = articleService.show(id);
        //비교 데이터(null 은 toString() 메서드를 호출 할수 없기때문에 안씀)
        assertEquals(expected, article);
    }

    @Test
    @Transactional//생성 변경 삭제될수 있는경우에는 추가해줘서 롤백 해야됨!
    void create_success() {
        //title과 content 만 있는 DTO

        //예상 데이터
        String title = "23123213123";
        String content ="123123123";

        ArticleFormDTO articleFormDTO = new ArticleFormDTO(null,title,content);
        //- eintity 에 집어넣어서
        Article expected = new Article(4L,title,content);

        //실제 데이터
        Article article = articleService.create(articleFormDTO);
        //비교 데이터
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_fail() {
        //id 가 포함된 DTO 가 입력이 됐다??

        //예상 데이터
        String title = "123123123123";
        String content ="ㅂㅈㄷㅈㅂㄷㅂㅈㄷ";

        ArticleFormDTO articleFormDTO = new ArticleFormDTO(4L,title,content);
        //- eintity 에 집어넣어서
        Article expected = null;

        //실제 데이터
        Article article = articleService.create(articleFormDTO);
        //비교 데이터
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공__존재하는_id와_title_content_가_있는_DTO_입력() {

        //예상 데이터
        Long id = 1L;
        String title = "아에이오";
        String content = "뭐이 시펄";
        ArticleFormDTO articleFormDTO = new ArticleFormDTO(id,title,content);
        Article exptected = new Article(id,title,content);
        //실제 데이터
        Article article = articleService.update(id,articleFormDTO);

        //비교 데이터
        assertEquals(exptected.toString(),article.toString());
    }

    @Test
    @Transactional
    void update_성공__존재하는_id와_title_만_있는_DTO_입력() {
        //예상 데이터
        Long id = 1L;
        String title = "아에이오";
        String content = null;
        ArticleFormDTO articleFormDTO = new ArticleFormDTO(id,title,content);
        Article exptected = new Article(1L,"아에이오","111111");
        //실제 데이터
        Article article = articleService.update(id,articleFormDTO);

        //비교 데이터
        assertEquals(exptected.toString(),article.toString());
    }
    @Test
    @Transactional
    void update_실패__존재하지_않는_id의_DTO_입력() {
        Long id = -1L;
        //예상 데이터
        String title = "이준서";
        String content = "이준서이준서";

        ArticleFormDTO articleFormDTO = new ArticleFormDTO(id, title,content);
        Article expected = null;

        //실제 데이터
        Article article = articleService.update(id,articleFormDTO);

        //데이터 비교

        assertEquals(expected,article);


    }


    @Test
    @Transactional
    void delete_성공__존재하는_id_입력() {
        Long id = 1L;
        //예상 데이터
        Article expecte = new Article(id,"111111","111111");
        //실제 데이터
        Article article = articleService.delete(id);
        //데이터 비교
        assertEquals(expecte.toString(), article.toString());
    }
    @Test
    @Transactional
    void delete_실패__존재하지_않는_id_입력() {
        Long id = -1L;
        Article expecte = null;
        //실제 데이터
        Article article = articleService.delete(id);
        //데이터 비교
       assertEquals(expecte,article);
    }
}