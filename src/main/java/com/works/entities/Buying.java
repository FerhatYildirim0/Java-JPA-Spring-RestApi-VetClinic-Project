package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "Buying", description = "Tedarikçilerden alınan ürünleri kaydetmek için kullanılır.")
@Entity
@Getter
@Setter
public class Buying {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid", nullable = false)
    private Integer bid;

    @ApiModelProperty(value = "Tedarikçi İsmi")
    private String tname;

    @ApiModelProperty(value = "Ürün İsmi")
    private String pname;

    @ApiModelProperty(value = "Not")
    private String bnote;

    @ApiModelProperty(value = "ürün Fiyatı")
    private int proprice;


    @ApiModelProperty(value = "ürün Miktarı")
    private int proamount;

    @ApiModelProperty(value = "ürün Birimi")
    private int probirim;

    @ApiModelProperty(value = "ürün Vergi Oranı Tipi")
    private int taxtype;

    @ApiModelProperty(value = "Ürün İndirimi")
    private int pdiscount;

    @ApiModelProperty(value = "Ödeme Tipi")
    private int purtype;

    @ApiModelProperty(value = "Vergi Dahil Toplam Ödeme Tutarı")
    private int btotalPrice;

    @ApiModelProperty(value = "Vergi Hariç Şirketin Eline Kalan Tutar")
    private int bnetPrice;

    @ApiModelProperty(value = "Vergi Hariç Toplam Tutar")
    private int bgrossPrice;

    @ApiModelProperty(value = "Fatura No")
    private int bfaturaNo;

    @ApiModelProperty(value = "Satın Alım Tarihi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyingDate;

    @ApiModelProperty(value = "Durum Değişkeni", notes = "Status=0 ise ürünler sepette, Status=1 ise Satın alınmış, Status=2 ise aynı fatura nodaki ürünlerin toplamını gösterir")
    private int status;
}
