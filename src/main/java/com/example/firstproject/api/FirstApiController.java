package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Rest API용 컨트롤러, JSON 반환
public class FirstApiController {
    
    // 일반적으로 그냥 controller 는 view 페이지를 반환하지만
    
    // RestController 는 json 형태를 반환한다, 텍스트도
    @GetMapping("/api/hello")
    public String hello(){
        return "나는 이준서다!!!!!!!!!!!!!!!!!!!";
    }
}
