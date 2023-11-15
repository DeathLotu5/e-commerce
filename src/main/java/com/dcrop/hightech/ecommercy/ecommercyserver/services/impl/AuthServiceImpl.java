package com.dcrop.hightech.ecommercy.ecommercyserver.services.impl;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.entity.Customer;
import com.dcrop.hightech.ecommercy.ecommercyserver.repository.CustomerRepository;
import com.dcrop.hightech.ecommercy.ecommercyserver.services.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public int login(AuthRequest request) {
        List<Customer> users = customerRepository.findByEmail(request.getEmail());
        if (!users.isEmpty()) {
            if (passwordEncoder.matches(request.getPassword(), users.get(0).getPwd())) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));

                new UsernamePasswordAuthenticationToken(users.get(0).getEmail(), users.get(0).getPwd(), authorities);
                return 1;
            } else {
                throw new BadCredentialsException("Password invalid!");
            }
        } else {
            throw new BadCredentialsException("No user registed with this details");
        }
    }

    @Override
    public int signUp(AuthRequest request) {

        List<Customer> users = customerRepository.findByEmail(request.getEmail());
        if (users.isEmpty()) {
            String hashedPwd = passwordEncoder.encode(request.getPassword());
            Customer user = new Customer();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPwd(hashedPwd);
            user.setMobileNumber(request.getMobileNumber());
            user.setCreateDt(new Date());
            user.setRole("MEMBER");
            customerRepository.save(user);

            return 0;
        }

        return -1;
    }


}
