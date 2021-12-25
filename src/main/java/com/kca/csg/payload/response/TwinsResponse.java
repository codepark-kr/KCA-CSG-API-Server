package com.kca.csg.payload.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TwinsResponse {

    @ApiModelProperty(example = "Twins 형식의 게시글 설명")
    private String korTitle;

    @ApiModelProperty(example = "Twins 형식의 게시글은 하나의 글을 한국어/영어 버전으로 작성하고 관리하기 위함입니다")
    private String korContent;

    @ApiModelProperty(example = "The description of Twins that the type of posts")
    private String engTitle;

    @ApiModelProperty(example = "The use of 'Twins', the type of posts is for write and manage to one article with two languages, Korean and English both")
    private String engContent;

    @ApiModelProperty(example = "About This site")
    private String category;

    @ApiModelProperty(example = "How to Use")
    private List<String> tags;

    public List<String> getTags(){ return tags == null ? null : new ArrayList<>(tags); }

    public void setTags(List<String> tags){
        if(tags == null){ this.tags = null; } else { this.tags = Collections.unmodifiableList(tags); }
    }
}
