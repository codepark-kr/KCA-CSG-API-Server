package com.kca.csg.dto;

import com.kca.csg.model.Twins;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TwinsDto {
    private String id;
    private String korTitle;
    private String korContent;
    private String engTitle;
    private String engContent;
    private Date enrollDate;
    private Date lastUpdate;

    public TwinsDto(final Twins entity){
        this.id = entity.getId();
        this.korTitle = entity.getKorTitle();
        this.korContent = entity.getKorContent();
        this.engTitle = entity.getEngTitle();
        this.engContent = entity.getEngContent();
    }

    public static Twins toEntity(final TwinsDto dto){
        return Twins.builder()
                .id(dto.getId())
                .korTitle(dto.getKorTitle()).korContent(dto.getKorContent())
                .engTitle(dto.getEngTitle()).engContent(dto.getEngContent())
                .enrollDate(dto.getEnrollDate()).lastUpdate(dto.getLastUpdate())
                .build();
    }
}
