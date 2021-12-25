package com.kca.csg.payload.request;

import com.kca.csg.model.Photo;
import com.kca.csg.model.User;
import com.kca.csg.payload.UserDateAuditPayload;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlbumRequest extends UserDateAuditPayload {

    private Long id;
    private String title;
    private User user;
    private List<Photo> photo;

    public List<Photo> getPhoto(){ return photo == null ? null : new ArrayList<>(photo);  }

    public void setPhoto(List<Photo> photo){
        if(photo == null){ this.photo = null; } else { this.photo = Collections.unmodifiableList(photo); } }
    }
