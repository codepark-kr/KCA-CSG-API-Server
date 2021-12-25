package com.kca.csg.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    @ApiModelProperty(example = "codepark.kr@gmail.com || codepark")
    private String usernameOrEmail;

    @NotBlank
    @ApiModelProperty(example = "example-password")
    private String password;
}
