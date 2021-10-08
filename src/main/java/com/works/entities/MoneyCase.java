package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "MoneyCase", description = "Para giriş çıkışlarının kayıt altına alındığı kısımdır")
@Entity
@Getter
@Setter
public class MoneyCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @ApiModelProperty(value = "Para Miktarı")
    private Integer cmoney;

    @ApiModelProperty(value = "Para Durum Değişkeni",  notes = "cbuysell=1 ise satın alım ekleme, cbuysell=2 satış ekleme")
    private Integer cbuysell; // 1 buy ekleme | 2 sell çıkarma

    @ApiModelProperty(value = "Kasa işlemi Tarihi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cdatenow;

    @ApiModelProperty(value = "İşlem Yapan Kullanıcının İsmi")
    private String cpeople;
    @ApiModelProperty(value = "Müşteri İsmi")
    private String cname;
    @ApiModelProperty(value = "İşlem Notu")
    private String ccomment;
}
