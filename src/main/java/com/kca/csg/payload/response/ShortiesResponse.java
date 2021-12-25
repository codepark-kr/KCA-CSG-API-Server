package com.kca.csg.payload.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShortiesResponse {

    @ApiModelProperty(example = "1")
    private Long firstThreadId;

    @ApiModelProperty(example = "1")
    private Long threadOrder;

    @ApiModelProperty(example = "Short articles on here")
    private String title;

    @ApiModelProperty(example = "Short articles that Less than 500 characters on here")
    private String content;
}
