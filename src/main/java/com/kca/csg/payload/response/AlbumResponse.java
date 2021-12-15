package com.kca.csg.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kca.csg.model.Photo;
import com.kca.csg.model.User;
import com.kca.csg.payload.UserDateAuditPayload;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumResponse  extends UserDateAuditPayload {

    private Long id;
    private String title;
    private User user;
    private List<Photo> photo;
    public List<Photo> getPhoto(){ return photo == null ? null : new ArrayList<>(photo); }

    public void setPhotos(List<Photo> photo){
        if(photo == null){ this.photo = null; } else { this.photo = Collections.unmodifiableList(photo); }
    }
}
