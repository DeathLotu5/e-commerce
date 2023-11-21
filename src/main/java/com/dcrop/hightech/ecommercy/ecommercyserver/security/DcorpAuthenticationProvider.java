package com.dcrop.hightech.ecommercy.ecommercyserver.security;

import com.dcrop.hightech.ecommercy.ecommercyserver.entity.AuthorityEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.UserEntity;
import com.dcrop.hightech.ecommercy.ecommercyserver.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DcorpAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<UserEntity> users = userRepository.findByUsername(username);
        if (users.isPresent()) {
            if (passwordEncoder.matches(pwd, users.get().getPassword())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                for (AuthorityEntity authority : users.get().getAuthorities()) {
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                }

                return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
            } else {
                throw new BadCredentialsException("Password invalid!");
            }
        } else {
            throw new BadCredentialsException("No user registed with this details");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
