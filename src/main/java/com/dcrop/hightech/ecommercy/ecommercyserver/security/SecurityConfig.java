package com.dcrop.hightech.ecommercy.ecommercyserver.security;

import com.dcrop.hightech.ecommercy.ecommercyserver.security.filters.JwtTokenGeneratorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .securityContext().requireExplicitSave(false)
//                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                /** Nêú không có 2 dòng trên, thì mỗi lần call api được bảo mật, ta sẽ luôn
                 * phải truyền lên thông tin đăng nhập của mình.
                 * @securityContext().requireExplicitSave(false):
                 * Cái này là để giao việc tạo ra JSESSIONID sẽ do spring quản lý và lưu
                 * trong spring context holder
                 * @sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                 * Cái này sẽ luôn tạo ra JSESSIONID khi t thực hiện login lần đầu.
                 */
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                    config.setAllowCredentials(true);
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);

                    return config;
                }).and().csrf(
                        csrf -> csrf
                                .csrfTokenRequestHandler(requestHandler)
                                .ignoringRequestMatchers("/auth/sign-up", "/auth/sign-in")
                                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        //Là để cho Js có thể đọc được cookie
                        )
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/notices", "/contact").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/sign-up", "/auth/sign-in").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
