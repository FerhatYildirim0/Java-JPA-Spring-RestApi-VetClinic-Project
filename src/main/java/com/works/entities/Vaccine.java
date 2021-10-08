package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@ApiModel(value = "Aşı", description = "Aşı eklemek için kullanılır")
@JsonDeserialize(contentAs =  Vaccine.class)
@Entity
@Data
@Table(name="vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacid", nullable = false)
    private Integer vacid;

    @NotNull(message = "Name parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Length(min = 2, max = 150, message = "Name min = 2, max = 100")
    private String vacname;

    @ApiModelProperty(value = "Aşı Birimi", notes = "Şişe, kutu, mL vs..")
    private Integer vacunit;

    @ApiModelProperty(value = "Aşı Tipi", notes = "Pet mi Çiftlik mi bunu saklar")
    private Integer vactype;

    @ApiModelProperty(value = "Aşı Kategorisi", notes = "Şişe, kutu, mL vs..")
    private Integer vaccategory;

    @ApiModelProperty(value = "Aşı Tedarikçisi")
    private Integer vacsupplier;

    @ApiModelProperty(value = "Aşı Barkodu")
    private String vacbarcode;

    @ApiModelProperty(value = "Aşı Kodu")
    private String vaccode;

    @ApiModelProperty(value = "Aşı Satın Alım Fiyatı")
    private Integer vacbuying;

    @ApiModelProperty(value = "Aşı Satış Fiyatı")
    private Integer vacsales;

    @ApiModelProperty(value = "Aşı Vergisi")
    private Integer vactax;

    @ApiModelProperty(value = "Aşı Vergi Durum Değişkeni")
    private Integer vactaxincluded;

    @ApiModelProperty(value = "Aşı Stoğu")
    private Integer vacstock;

    @ApiModelProperty(value = "Aşı Durum Değişkeni", notes = "1 ise Aktif, 0 ise Pasif")
    private Integer vacactive;

    @ApiModelProperty(value = "Evcil Hayvan Aşı Tipi", notes = "Kedi, köpek vs..")
    private Integer vacpettype;

    @ApiModelProperty(value = "Aşı Tekrar Sayısı")
    private Integer vacrepeat;


    @OneToMany(mappedBy = "stvac", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)

    private List<Storage> storages;

}
