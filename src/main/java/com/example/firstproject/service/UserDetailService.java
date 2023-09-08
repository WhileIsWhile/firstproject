package com.example.firstproject.service;

import com.example.firstproject.entity.User;
import com.example.firstproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    /*UserDetailService 인터페이스를 구현 하고 UserDetailsService 상속해
    loadUserByUsername 메서드를 오버라이딩해서 사용자 정보를 가져오는 로직 작성
     */
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email){
        /*
            loadUserByUsername 사용자의 이름(email) 을 받아와서
            해당 사용자에 대한 정보를 데이터베이스나 다른 저장소에서 가져와야함
            시큐리티는 사용자가 입력한 인증 정보와 저장된 사용자 정보를 비교해서
            사용자를 인증
         */
        return userRepository.findByEmail(email)
                /*이메일 기반으로 데이터베이스에서 사용자 정보를 검색해 사용자를 조회하기
                위한 쿼리
                 */
                .orElseThrow(() -> new IllegalArgumentException(email));
                /*
                    검색한 결과가 없을경우 예외를 던짐, 사용자가 입력한 이메일에 해당하는
                    사용자가 없다면 예외를 발생시켜 처리하는부분
                 */
        /*
            return 은 검색 결과로 얻은 사용자 정보를 반환함 사용자 시큐리티에서
            인증 과정 중에 비교 및 확인되고 , 사용자가 올바른 인증 정보를 입력한경우에만 로그인에
            허용이 됨
         */

    }

}
