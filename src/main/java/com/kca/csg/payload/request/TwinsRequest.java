package com.kca.csg.payload.request;

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
    private String korTitle;

    @NotBlank
    @Size(min = 10)
    private String korContent;

    @NotBlank
    @Size(min = 10)
    private String engTitle;

    @NotBlank
    @Size(min = 10)
    private String engContent;

    @NotNull
    private Long categoryId;

    private List<String> tags;

    public List<String> getTags(){ return tags == null ? Collections.emptyList() : new ArrayList<>(tags); }

    public void setTags(List<String> tags){
        if(tags == null){ this.tags = null; } else { this.tags = Collections.unmodifiableList(tags); }}
}
