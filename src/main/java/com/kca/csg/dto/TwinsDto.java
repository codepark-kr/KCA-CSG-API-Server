package com.kca.csg.dto;

import com.kca.csg.model.Twins;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TwinsDto {
    private long id;
    private String korTitle;
    private String korContent;
    private String engTitle;
    private String engContent;

    public TwinsDto(final Twins entity){
        this.id = entity.getId();
        this.korTitle = entity.getKorTitle();
        this.korContent = entity.getKorContent();
        this.engTitle = entity.getEngTitle();
        this.engContent = entity.getEngContent();
    }
}
