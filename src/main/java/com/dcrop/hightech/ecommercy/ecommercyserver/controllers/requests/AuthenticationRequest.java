package com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {

    String email;
    String password;

}
