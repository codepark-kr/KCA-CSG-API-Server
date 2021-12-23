package com.kca.csg.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {

    @NotBlank
    @Size(min = 4, max = 40)
    @ApiModelProperty(example = "code")
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 40)
    @ApiModelProperty(example = "park")
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 15)
    @ApiModelProperty(example = "codepark")
    private String username;

    @NotBlank
    @Size(max = 40)
    @Email
    @ApiModelProperty(example = "codepark.kr@gmail.com")
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    @ApiModelProperty(example = "example-password")
    private String password;

    @Size(min = 12, max = 20)
    @ApiModelProperty(example = "00000000000")
    private String contact;
}
