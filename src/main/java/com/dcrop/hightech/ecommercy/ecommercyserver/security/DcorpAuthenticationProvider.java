//package com.dcrop.hightech.ecommercy.ecommercyserver.security;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@AllArgsConstructor
//public class DcorpAuthenticationProvider implements AuthenticationProvider {
//
//    private final CustomerRepository customerRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        List<Customer> users = customerRepository.findByEmail(username);
//        if (!users.isEmpty()) {
//            if (passwordEncoder.matches(pwd, users.get(0).getPwd())) {
//                List<GrantedAuthority> authorities = new ArrayList<>();
//                authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
//
//                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
//            } else {
//                throw new BadCredentialsException("Password invalid!");
//            }
//        } else {
//            throw new BadCredentialsException("No user registed with this details");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
