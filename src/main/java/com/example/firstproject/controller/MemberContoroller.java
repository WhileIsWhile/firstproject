package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class MemberContoroller {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member")
    public String member(){
        return"articles/member";
    }
    @PostMapping("/member/create")
    public String createMember(MemberForm memberForm){

        log.info(memberForm.toString());
        Member member = memberForm.toEintity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);

        return "";
    }
}
