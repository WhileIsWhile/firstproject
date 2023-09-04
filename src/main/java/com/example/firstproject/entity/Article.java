package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // DB 가 해당객체를 인식 가능!
@AllArgsConstructor     //모든 생성자를 만들어줌
@NoArgsConstructor  //디폴트 생성자를 추가해주는 어노테이션
@ToString
@Getter
public class Article {

    @Id//대표값을 지정! 하는 어노테이션 주민등록번호같은것,
    // 제목도 같고 내용도 같을수 있지만 다른 애를 확인할수있게!!하는 것!
    // 1. 2.3. 자동 생성 어노테이션 대표값들 이 자동으로 생성 하게해줌!! 였지만
    // strategy = GenerationType.IDENTITY 이렇게 함으로써 db가 알아서 번호 되도록 한것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;



  /*  public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }*/
/*    public Article() {
 *//*     빈생성자 쓰는 이유는
        여기서 귀하의 질문은 "왜 Article 클래스에 기본 생성자를 만들어야 하는지?"입니다.JPA에서 Entity 클래스에는 기본 생성자를 가져야 하는 이유는
        JPA가 Entity를 생성하고 매핑하기 위해서입니다.
        JPA는 객체를 생성한 후에 setter 메서드를 사용하여 필드 값을 설정하는 방식이 아니라,
        기본 생성자를 통해 객체를 생성한 후에 필드 값을 설정합니다.
        기본 생성자가 없을 경우 JPA가 Entity를 생성하는 과정에서 문제가 발생할 수 있습니다.
        따라서 JPA에서는 Entity 클래스에 기본 생성자가 있어야 하며,
        귀하의 코드에서와 같이 기본 생성자를 추가해주는 것이 좋은 방법입니다.*//*

    }*/

 /*   @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/
}
