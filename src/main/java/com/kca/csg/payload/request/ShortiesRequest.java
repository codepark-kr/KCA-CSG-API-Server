package com.kca.csg.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.annotation.Nullable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ShortiesRequest {

    @Nullable
    @ApiModelProperty(example = "1")
    private Long firstThreadId;

    @NotBlank
    @ApiModelProperty(example = "1")
    private Long threadOrder;

    @Nullable
    @Size(min = 4, max = 30)
    @ApiModelProperty(example = "Short articles on here")
    private String title;

    @NotBlank
    @Size(max = 500)
    @ApiModelProperty(example = "Short articles that Less than 500 characters on here")
    private String content;
}