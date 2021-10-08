package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
@ApiModel(value = "CalendarInfo", description = "Takvim üstünden Yeni Randevu Eklemek için Kullanılır.")
@Entity
@Data
public class CalendarInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    private String cname;
    private String ccolor;
    private String cbgColor;
    private String cdragBgColor;
    private String cborderColor;

}
