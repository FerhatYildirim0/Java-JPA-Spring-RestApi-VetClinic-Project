package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Suppliers", description = "Yeni Tedarikçi Eklemek için Kullanılır.")
@Entity
@Getter
@Setter
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suid", nullable = false)
    private Integer suid;

    @ApiModelProperty(value = "Tedarikçi İsmi", required = true, notes = "Tedarikçi İsmi belirlenmezse hata meydana gelir!")
    @NotNull(message = "Suame parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Length(min = 2, max = 100, message = "Name min = 2, max = 100")
    private String suname;

    @ApiModelProperty(value = "Tedarikçi Soyismi", required = true, notes = "Email belirlenmezse hata meydana gelir!")
    @NotNull(message = "Sumail parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    private String sumail;

    @ApiModelProperty(value = "Tedarikçi Telefonu", required = true)
    private String sutel;

    @ApiModelProperty(value = "Tedarikçi Durum Bilgisi", required = true, notes = "Tedarikçiyle aktif olarak alışveriş yapılıp yapılmadığını belirtir.")
    private Integer suactive;

    @ApiModelProperty(value = "Tedarikçi Borcu", required = true, notes = "Aktif olarak sürekli değişen bir parametredir.")
    private Integer sudebt;


}
