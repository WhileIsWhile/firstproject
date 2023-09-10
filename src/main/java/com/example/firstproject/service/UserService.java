package com.example.firstproject.service;

import com.example.firstproject.dto.AddUserRequestDTO;
import com.example.firstproject.entity.User;
import com.example.firstproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequestDTO addUserRequestDTO){

        return userRepository.save(User.builder()
                .email(addUserRequestDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(addUserRequestDTO.getPassword()))
                .build()).getId();


    }
}
