package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
@ApiModel(value = "ScheduleCalendar", description = "Belli olan randevuları takvimde göstermek için kullanılır")
@Entity
@Data
public class ScheduleCalendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    private String id;
    @ApiModelProperty(value = "Takvim Başlığı")
    private String title;
    @ApiModelProperty(value = "Durum Değişkeni", notes = "0 ise tüm gün değil,1 ise tüm gün")
    private Boolean isAllDay;

    @ApiModelProperty(value = "Randevu Başlangıç")
    private String start;
    @ApiModelProperty(value = "Randevu Bitiş")
    private String end;
    @ApiModelProperty(value = "Randevu Kategorisi")
    private String category;
    private String dueDateClass;
    private String color;
    private String bgColor;
    private String dragBgColor;
    private String borderColor;
    @ApiModelProperty(value = "Randevu Konumu")
    private String location;
    private String raw;
    @ApiModelProperty(value = "Randevu Durumu")
    private String state;
    private String calendarId;

}
