package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  //1. 먼저 어노테이션 컨트롤러를 추가 하고.
public class FirstController {

    @GetMapping("/hi") //3. GetMapping 으로 연결해주면됨
    public String niceToMeetYou(Model moel){ // 2. 반환형 String 으로 return 값을 어디 파일에 받을건지 파일 이름으로 리턴값을 설정하고

        //이준서라는 value 값을 이름을 username에 key 값에 연결시켜서 보내준다 model 이라는 객체에다가
        moel.addAttribute("username","JunSeo");

        return "greetings"; // 반환이 templates/greetings.mustache 로 감
    }

    @GetMapping("/bye")
    public String bye(Model model){
        model.addAttribute("username","이준서");
        return "bye";
    }


}
