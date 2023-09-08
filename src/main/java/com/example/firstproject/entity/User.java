package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name="users")
/*
    외부에서 생성자를 호출 방지 '클래스 생성자' 를 protected 로 설정
    함으로써 해당 엔티티를 다른 패키지나 클래스에서 직접 생성하는 것을
    막을수 있음!!!
    .....이렇게 설정된 생성자는 하위 클래스 에서는 호출 할수 있으므로
    필요한 경우 상속 관계를 고려하여 사용할수있음.

*/
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false , unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email,String password, String auth){
        this.email = email;
        this.password = password;
        /*
            String auth 를 쓰는 이유는 사용자의 권한을 나타낼떄 씀
            필요 할떄 (권한정보)  auth 값 을 설정할수 있음
            만약 권한 설정 이 안되있으면 auth  필드는 기본 값이나
            null 로 초기화됨

            빌더 패턴 예시
            User user = User.builder()
                        .email("~~@~~")
                        .password("~~")
                        .auth("ROLE_USER")
                        .build();
         */
    }


    @Override   //권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
            SimpleGrantedAuthority 는 권한(역할) 을 나타내는 클래스 이고
            SimpleGrantedAuthority("user") 함으로써 user 라는 문자열을 가지고
            간단한 객체를 생성
            List.of() 는 불변한 리스트를 생성한다는 말이고

            return List.of(new SimpleGrantedAuthority("user"));를 쓰면

            권한을 user 객체를 새로 생성해 불변한 리스트 값에 담아 반환!

         */
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override   //사용자의 id 반환(고유 값)
    public String getUsername() {
        return email;
    }
    @Override   //사용자의 패스워드 반환
    public String getPassword(){
        return password;
    }

    @Override   //계정 만료 여부 반환
    public boolean isAccountNonExpired() {
        return true;    //true : 만료x
    }

    @Override   //계정 잠금 여부 반환
    public boolean isAccountNonLocked() {
        return true;    //true : 잠금x
    }

    @Override   //패스워드 만료 여부 반환
    public boolean isCredentialsNonExpired() {
        return true;    //true : 만료x
    }

    @Override   //계정 사용 가능 여부 반환
    public boolean isEnabled() {
        return true; // true : 사용가능
    }
}
