package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "PasswordChangeToken", description = "Yeni şifre belirlemek için kullanılır")
@Entity
@Getter
@Setter
public class PasswordChangeToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "token",  notes = "Şifre Değiştirmek İsteyen Kişiye özel üretilen bir id.")
    private String token;

    @OneToOne( fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private User user;

    @ApiModelProperty(value = "Şifre Değiştirme Tarihi")
    private Date expiryDate;



}