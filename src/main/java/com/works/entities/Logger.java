package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@ApiModel(value = "Logger", description = "Oturum açan kimsenin yaptığı işlemleri kayıt altına alır")
@Entity
@Data
public class Logger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid", nullable = false)
    private Integer lid;

    @ApiModelProperty(value = "İşlem yapan kişinin ismi")
    private String lname;

    @ApiModelProperty(value = "İşlem yapan kişinin soyismi")
    private String lsurname;
    @ApiModelProperty(value = "İşlem yapan kişinin emaili")
    private String lemail;
    @ApiModelProperty(value = "İşlem yapan kişinin SessionID'si")
    private String lsessionId;
    @ApiModelProperty(value = "İşlem yapan kişinin IP'si")
    private String lIp;
    @ApiModelProperty(value = "İşlem yapan kişinin Rolleri (Yetki Seviyeleri)")
    private String lroles;
    @ApiModelProperty(value = "İşlem yapılan sitenin adresi")
    private String lUrl;
    @ApiModelProperty(value = "İşlem yapılan tarih")
    private LocalDateTime lDate;
}
