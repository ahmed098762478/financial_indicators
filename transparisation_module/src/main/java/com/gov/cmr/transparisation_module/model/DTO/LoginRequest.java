package com.gov.cmr.transparisation_module.model.DTO;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
