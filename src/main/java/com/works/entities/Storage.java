package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stid", nullable = false)
    private Integer stid;

    @ApiModelProperty(value = "Depo Durum Değişkeni", notes = "1 ise Ekleme, 2 ise Güncelleme, 3 ise Çıkarma")
    private Integer staction; // Add 1 - Update 2 - Sale 3

    @ApiModelProperty(value = "Depo İşlem Tarihi")
    private Date stdate;

    @ApiModelProperty(value = "Stok Değişim Miktarı")
    private Integer stchangeamount;
    @ApiModelProperty(value = "Son stok miktarı")
    private Integer stlastamount;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stvac")
    @JsonIgnore
    private Vaccine stvac;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stpro")
    @JsonBackReference
    private Product stpro;


}
