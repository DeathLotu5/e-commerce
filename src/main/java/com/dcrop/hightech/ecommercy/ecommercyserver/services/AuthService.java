package com.dcrop.hightech.ecommercy.ecommercyserver.services;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthRequest;

public interface AuthService {

    String login(AuthRequest request);
    void register(AuthRequest request);

}
