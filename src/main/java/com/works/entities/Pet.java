package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@ApiModel(value = "Pet", description = "Müşterilerin üstüne kayıtlı evcil hayvanlar")
@Getter
@Setter
@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer pid;

    @ApiModelProperty(value = "Pet Adı", required = true, notes = "Name belirlenmezse hata meydana gelir!")
    @NotNull(message = "Pname parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    private String pname;

    @ApiModelProperty(value = "Chip numarası")
    private Integer pchip;

    @ApiModelProperty(value = "Küpe numarası")
    private String pearnumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pbirthday;

    @ApiModelProperty(value = "Pet türü")
    private Integer ptype;
    private Integer pspay;
    private String prace;

    @ApiModelProperty(value = "Pet Rengi")
    private Integer pcolor;

    @ApiModelProperty(value = "Pet Cinsiyeti")
    private Integer pgender;




    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cid", nullable = false)
    @JsonBackReference
    private Customer cid;

}
