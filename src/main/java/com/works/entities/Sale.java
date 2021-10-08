package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "Sale", description = "Satış yaparken Kullanılır.")
@Entity
@Getter
@Setter
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Integer sid;

    @ApiModelProperty(value = "Durum Değişkeni", notes = "Status=0 ise ürünler sepette, Status=1 ise Satın alınmış anlamına gelir")
    private boolean status;

    @ApiModelProperty(value = "Müşteri İsmi")
   private String cname;

    @ApiModelProperty(value = "Ürün İsmi")
   private String pname;

    @ApiModelProperty(value = "Fatura No")
   private int faturaNo;

    @ApiModelProperty(value = "Ürün Fiyatı")
   private int pprice;

    @ApiModelProperty(value = "Satış Yapılan Tarih")
   @DateTimeFormat(pattern = "yyyy-MM-dd")
   private Date saleDate;


   @Column(length = 250)
   private String note;
    @ApiModelProperty(value = "Vergi Dahil Toplam Tutar")
   private int totalPrice;

    @ApiModelProperty(value = "Ürün Miktarı")
   private int pamount;

    @ApiModelProperty(value = "Vergi Hariç Toplam Tutar")
    private int grossPrice;

    @ApiModelProperty(value = "Vergi Hariç Şirkete Kalan Toplam Tutar")
   private int netPrice;
    @ApiModelProperty(value = "Ürün Tipi")
    private int proType;

    @ApiModelProperty(value = "Vergi Oranı Tipi")
    private int tax_type;
    @ApiModelProperty(value = "İndirim Oranı")
    private int pdiscount;
    @ApiModelProperty(value = "Ödeme Tipi")
    private int purType;

}
