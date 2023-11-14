//package com.BizEcho.shoppingplatform.config;
//
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/users/register").permitAll()
//                        .anyRequest().authenticated()
//                )
//        // ... 其他安全配置 ...
//        ;
//        return http.build();
//    }
//
//    // ... 其他 Bean 定义，例如 PasswordEncoder ...
//}
//
