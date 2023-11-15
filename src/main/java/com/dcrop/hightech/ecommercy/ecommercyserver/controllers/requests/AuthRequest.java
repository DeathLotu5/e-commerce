package com.dcrop.hightech.ecommercy.ecommercyserver.controllers.requests;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequest {

    String name;
    String email;
    String password;
    String mobileNumber;


}