package com.dcrop.hightech.ecommercy.ecommercyserver.controllers.responses;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {

    String token;

}
