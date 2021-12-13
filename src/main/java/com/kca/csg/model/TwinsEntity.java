package com.kca.csg.model;

import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TWINS")
public class TwinsEntity {
    private long id;
    private String korTitle;
    private String korContent;
    private String engTitle;
    private String engContent;
    private Timestamp enrollDate;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "KOR_TITLE")
    public String getKorTitle() {
        return korTitle;
    }

    public void setKorTitle(String korTitle) {
        this.korTitle = korTitle;
    }

    @Basic
    @Column(name = "KOR_CONTENT")
    public String getKorContent() {
        return korContent;
    }

    public void setKorContent(String korContent) {
        this.korContent = korContent;
    }

    @Basic
    @Column(name = "ENG_TITLE")
    public String getEngTitle() {
        return engTitle;
    }

    public void setEngTitle(String engTitle) {
        this.engTitle = engTitle;
    }

    @Basic
    @Column(name = "ENG_CONTENT")
    public String getEngContent() {
        return engContent;
    }

    public void setEngContent(String engContent) {
        this.engContent = engContent;
    }

    @Basic
    @Column(name = "ENROLL_DATE")
    public Timestamp getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Timestamp enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Basic
    @Column(name = "LAST_UPDATE")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwinsEntity that = (TwinsEntity) o;
        return id == that.id && Objects.equals(korTitle, that.korTitle) && Objects.equals(korContent, that.korContent) && Objects.equals(engTitle, that.engTitle) && Objects.equals(engContent, that.engContent) && Objects.equals(enrollDate, that.enrollDate) && Objects.equals(lastUpdate, that.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, korTitle, korContent, engTitle, engContent, enrollDate, lastUpdate);
    }
}
