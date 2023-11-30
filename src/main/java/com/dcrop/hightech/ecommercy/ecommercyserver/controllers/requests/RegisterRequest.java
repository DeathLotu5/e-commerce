package com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {

    String firstname;

    String lastname;

    String email;

    String password;
}
