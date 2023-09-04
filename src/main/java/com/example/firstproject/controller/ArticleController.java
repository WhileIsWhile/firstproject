package com.example.firstproject.controller;


import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅을 위한 골뱅이(어노테이션)
public class ArticleController {

    //3번째로 한것!
    @Autowired//스프링 부트가 미리 생성해 놓은 객체를 가져다가 연결! 자동 연결!
    private ArticleRepository articleRepository;

    // 1번째 로 한것
    @GetMapping("/articles/new")
    public String newArticleForm(){

        return"articles/new";
    }
    //2번째로 한것
    @PostMapping("articles/create")
    //던져진 form 객체는 dto 객체에 담겨 지더라....ArticleForm articleForm
    public String createArticle(ArticleForm articleForm){
       /* System.out.println(articleForm.toString());
       => 로깅으로 대체!!!
        */
        log.info(articleForm.toString());

        // 1. Dto 를 변환!! -> Entity 로!
        // articleForm.toEntity(); ArticleDto를 엔티티로 !!
        // 그럼 엔티티에 담아야겠지?
        // Article article =
        // Article 클래스 엔티티를 만들고 변수명을 artcle 에 = articleForm.toEntity(); 를 담아주는것!

        Article article = articleForm.toEntity();
        /*System.out.println(article.toString());
        * => 로깅으로 대체!!
         */
        log.info(article.toString());

        // 2. Repository 에게 Entity 를 DB 안에 저장하게 함!
        // 그럼 artcle 에 는 dto 에서 >entity 바뀐것이 담겨잇을꺼고 그부분을 저장해야하니까
        // Article 엔터티이에 saved 변수명 을 만들고 articleRepository 통해 저장해야함! 그럼
        // ArticleRepository 에  articleRepository
        Article saved = articleRepository.save(article);
        /*System.out.println(saved.toString());
        * =>로깅으로 대체!!!!
        * */
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    
    //GetMapping 으로 url 주소값을 불러올때 id 로 불러올려면(파라미터 값을 불러올려면)
    // {id} 를 해야하고!! 여기서 id는 PathVariable 로 불러올수있음
    public String show(@PathVariable Long id, Model model){

        log.info("id : " + id);

        // 1. id로 데이터를 가져옴!
        //id 데이터를 찾아서 없다면 null 을 반환해라! 라는 뜻 으로 .orElse(null) 이걸 씀

        //아니면 articleRepository.findById(id); 반환 값이 Optional 이기 때문에 거기에서 .get();
        //메서드를 들고와서 모델에 담으면됨!!

        /*Article article= articleRepository.findById(id).orElse(null);*/ //이렇게 써도 되지만 ??

        Optional<Article> article = articleRepository.findById(id);

        // 2. 가져온 데이터를 모델에 등록!

        model.addAttribute("article", article.get());

        // 3. 보여줄 페이지를 설정!
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져온다.
        //해당 리파지토리의 모든 데이터를 가져온다! .findAll()

        //articleRepository.findAll(); 얘가 리턴하는 타입으로 캐스팅하는법
        /*Iterable<Article>articleEntityList = articleRepository.findAll();*/

        // List<Article> 타입으로 캐스팅 하는방법!
        List<Article> articleEntityList =  (List<Article>)articleRepository.findAll();


        // 2. 가져온 Article 묵음을 뷰로 전달.
        model.addAttribute("articleList",articleEntityList );

        // 3. 뷰페이지 를 설정!
        return "articles/index";//articles 안에 index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String eidit(@PathVariable Long id , Model model){
        //수정할 데이터 가져오기!!
       Optional<Article> article = articleRepository.findById(id);
        
       //모델에 데이터 등록하기
       model.addAttribute("article",article.get());


        //뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm articleForm){
        log.info(articleForm.toString());

        // 1. DTO 를 엔티티로 변환한다!
        Article article = articleForm.toEntity();

        log.info(article.toString());

        // 2. 엔티티를 DB 로 저장한다
        // 2-1 DB에서 기존 데이터를 가져온다!!!

         /*Article target = articleRepository.findById(article.getId()).orElse(null);*/
         Optional<Article> target = articleRepository.findById(article.getId());


         // 2-2 기존 데이터 값을 갱신한다.
         if(target != null){
            articleRepository.save(article); // 엔티티가 DB로 갱신된다.
         }


        // 3. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id , RedirectAttributes rttr){

        log.info("삭제요청이 들어왔습니다.");

        // 1. 삭제 대상을 가져온다.

        /*Article article = articleRepository.findById(id).orElse(null);
        *  대상이 없으면 null 로 리턴이 된다. */

        Optional<Article> article  = articleRepository.findById(id);

        log.info(article.toString());

        // 2. 그 대상을 삭제한다.
        if(article != null){
            articleRepository.delete(article.get());
            rttr.addFlashAttribute("msg", "삭제가 완료 되었습니다");
        }

        // 3. 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles";
    }


}
