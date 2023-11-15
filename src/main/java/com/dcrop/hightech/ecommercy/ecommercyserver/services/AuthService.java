package com.dcrop.hightech.ecommercy.ecommercyserver.services;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthRequest;

public interface AuthService {

    int login(AuthRequest request);

    int signUp(AuthRequest request);

}
