package com.kca.csg.payload.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ExceptionResponse {

    @ApiModelProperty(example = "Not Found")
    private String error;

    @ApiModelProperty(example = "404")
    private Integer status;

    @ApiModelProperty(example = "No message available")
    private List<String> messages;

    @ApiModelProperty(example = "2021-12-22T16:09:54.619+09:00")
    private Instant timestamp;

    public ExceptionResponse(List<String> messages, String error, Integer status){
        setMessages(messages);
        this.error = error;
        this.status = status;
        this.timestamp = Instant.now();
    }

    public List<String> getMessages(){ return messages == null ? null : new ArrayList<>(messages); }

    public final void setMessages(List<String> messages){
        if(messages == null){ this.messages = null; } else { this.messages = Collections.unmodifiableList(messages); }
    }
}
