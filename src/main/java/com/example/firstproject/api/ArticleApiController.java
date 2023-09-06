package com.example.firstproject.api;


import com.example.firstproject.dto.ArticleFormDTO;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// RestAPI 용 컨트롤ㄹ러 데이터(JSON)를  반환한다.
@Slf4j
public class ArticleApiController {
    @Autowired // 외부에서 가져옴 , DI 생성 객체를 가져와 연결!
    private ArticleService articleService;


    //Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }
  @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){

        //return articleRepository.findById(id).orElse(null);

        return articleService.show(id);
    }


    //Post
    @PostMapping("/api/articles")
    //@RequestBody JSON 데이터 받기!! 이렇게 해야 null 값이 안나옴
    public ResponseEntity<Article> create(@RequestBody ArticleFormDTO articleFormDTO){

        //ArticleFormDTO articleFormDTO DTO 에서 데이터를 받아와주고
        /*Article article = articleFormDTO.toEntity();*/

        Article created = articleService.create(articleFormDTO);


        return (created != null) ? ResponseEntity.status(HttpStatus.OK).body(created)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id , @RequestBody ArticleFormDTO articleFormDTO) {



      Article updated = articleService.update(id,articleFormDTO);

        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) :
                 ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //Delete
    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

       Article deleted = articleService.delete(id);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트렌젝션 => 실패 => 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleFormDTO> articleFormDTOS){

        List<Article> createList = articleService.createArticles(articleFormDTOS);

        return (createList != null) ?
            ResponseEntity.status(HttpStatus.OK).body(createList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
