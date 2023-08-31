package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


//Repository를 직접 구현할수도 있지만 JPA 에서 제공 하는 Repository 인터페이스를 활용해서 쉽게만들기!!

//ArticleRepository는 extends CrudRepository 제공하는 리파지토리를 사용할수있다 <> 를 추가해서 2개의
// 파라미터를 넣어줄수잇다!!! <Article,> 관리대상 엔티티를 먼저 앞에 넣어주고 , 그다음 <Article, Long>
// Article 엔티티에서의 대표값의 타입을 넣어줘야 한다 즉 @Id private Long id; 에있는 대표값 타입을 넣어주는것

//이렇게 해주면 crud 즉 추가 코드 없이 이렇게 사용할수있따. 확장 받아서 사용가능
public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();
}
