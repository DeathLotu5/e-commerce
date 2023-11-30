package com.dcrop.hightech.ecommercy.ecommercyserver.services;

import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.AuthenticationRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests.RegisterRequest;
import com.dcrop.hightech.ecommercy.ecommercyserver.controllers.responses.AuthenticationResponse;

public interface AuthService {


    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
