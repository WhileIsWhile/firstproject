package com.example.firstproject.dto;


//form 데이터를 받아올 그릇

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;    //id 필드 추가
    private String title;
    private String content;


    // AllArgsConstructor 써준 이유!!! - 리팩터링 이라고 함!(가독성이 좋아짐!)

   /* public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //잘 받았는지 확인하기위해서!!
    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    public Article toEntity() {

        return new Article(id,title,content);
    }
}
