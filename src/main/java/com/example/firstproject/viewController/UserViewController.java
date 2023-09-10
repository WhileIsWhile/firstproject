package com.example.firstproject.viewController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserViewController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    //로그아웃 추가
    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse){
        new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse
        , SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/logout";
    }
}
