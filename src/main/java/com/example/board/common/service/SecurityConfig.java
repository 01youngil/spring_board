package com.example.board.common.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //spring security filter설정을 customizing 하기 위한 어노테이션
@EnableGlobalMethodSecurity(prePostEnabled = true) //pre true: 사전검증, pre false : 사후검증
public class SecurityConfig {
    @Bean
    public SecurityFilterChain myFilter(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//                csrf공격에 대한 설정은 하지 않겠다는 의미.
                .csrf().disable()
                .authorizeRequests().antMatchers("/","/author/create","/author/login").permitAll()
                                    .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/author/login")
//                          사전에 구현돼있는 doLogin 메서드 그대로 사용
                            .loginProcessingUrl("/doLogin")
//                          다만, doLogin에 넘겨줄 email, password의 변수명은 별도
                            .usernameParameter("email")
                            .passwordParameter("password")
                            .successHandler(new LoginSuccessHandler())
                .and()
//                          spring security에 사전구현돼있는 doLogout메서드 그대로 활용.
                            .logout().logoutUrl("/doLogout")
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
