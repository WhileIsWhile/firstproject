package com.example.firstproject.api;

import com.example.firstproject.dto.AddUserRequestDTO;
import com.example.firstproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserAipController {
    private final UserService userService;

    @PostMapping("/user")
    public String signup(AddUserRequestDTO addUserRequestDTO){
        userService.save(addUserRequestDTO);
        return "redirect:/login";
    }
}
