package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
@ApiModel(value = "Ürün", description = "Hastanede kullanılacak malzemelerin kayıt edildiği özellikler")
@Entity
@Getter
@Setter
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prid", nullable = false)
    private Integer prid;

    @ApiModelProperty(value = "Ürün İsmi", required = true, notes = "Prname belirlenmezse hata meydana gelir!")
    @NotNull(message = "Name parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Length(min = 2, max = 150, message = "Name min = 2, max = 100")
    private String prname;

    @ApiModelProperty(value = "Ürün Tipi")
    private Integer prunit;
    @ApiModelProperty(value = "Ürün Tipi")
    private Integer prtype;
    @ApiModelProperty(value = "Ürün Kategorisi")
    private Integer prcategory;
    @ApiModelProperty(value = "Ürün Tedarikçisi")
    private Integer prsupplier;
    @ApiModelProperty(value = "Ürün Barkodu")
    private String prbarcode;
    @ApiModelProperty(value = "Ürün Kodu")
    private String prcode;
    @ApiModelProperty(value = "Ürün Satın Alım Fiyatı")
    private Integer prbuying;
    @ApiModelProperty(value = "Ürün Satış Fiyatı")
    private Integer prsales;
    @ApiModelProperty(value = "Ürün Vergi Tipi")
    private Integer prtax;
    @ApiModelProperty(value = "Ürün Durum Değişkeni", notes = "1 ise kullanımda, 0 ise kullanımda değil")
    private Integer practive;

    @ApiModelProperty(value = "Vergi Durum Değişkeni")
    private Integer prtaxincluded;
    @ApiModelProperty(value = "Stoktaki Ürün Miktarı")
    private Integer prstock;


    @OneToMany(mappedBy = "stpro", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Storage> storages;


}
