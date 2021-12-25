package com.kca.csg.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class TwinsRequest {

    @NotBlank
    @Size(min = 10)
    @ApiModelProperty(example = "Twins 형식의 게시글 설명")
    private String korTitle;

    @NotBlank
    @Size(min = 10)
    @ApiModelProperty(example = "Twins 형식의 게시글은 하나의 글을 한국어/영어 버전으로 작성하고 관리하기 위함입니다")
    private String korContent;

    @NotBlank
    @Size(min = 10)
    @ApiModelProperty(example = "The description of Twins that the type of posts")
    private String engTitle;

    @NotBlank
    @Size(min = 10)
    @ApiModelProperty(example = "The use of 'Twins', the type of posts is for write and manage to one article with two languages, Korean and English both")
    private String engContent;

    @NotNull
    @ApiModelProperty(example = "1")
    private Long categoryId;

    @ApiModelProperty(example = "How to Use")
    private List<String> tags;

    public List<String> getTags(){ return tags == null ? Collections.emptyList() : new ArrayList<>(tags); }

    public void setTags(List<String> tags){
        if(tags == null){ this.tags = null; } else { this.tags = Collections.unmodifiableList(tags); }}
}
