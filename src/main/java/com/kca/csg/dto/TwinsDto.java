package com.kca.csg.dto;

import com.kca.csg.model.TwinsEntity;
import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TwinsDto {
    private long id;
    private String korTitle;
    private String korContent;
    private String engTitle;
    private String engContent;
    private Timestamp enrollDate;
    private Timestamp lastUpdate;

    public TwinsDto(final TwinsEntity entity){
        this.id = entity.getId();
        this.korTitle = entity.getKorTitle();
        this.korContent = entity.getKorContent();
        this.engTitle = entity.getEngTitle();
        this.engContent = entity.getEngContent();
        this.enrollDate = entity.getEnrollDate();
        this.lastUpdate = entity.getLastUpdate();
    }

    public static TwinsEntity toEntity(final TwinsDto dto){
        return TwinsEntity.builder()
                .id(dto.getId())
                .korTitle(dto.getKorTitle()).korContent(dto.getKorContent())
                .engTitle(dto.getEngTitle()).engContent(dto.getEngContent())
                .enrollDate(dto.getEnrollDate()).lastUpdate(dto.getLastUpdate())
                .build();
    }
}
