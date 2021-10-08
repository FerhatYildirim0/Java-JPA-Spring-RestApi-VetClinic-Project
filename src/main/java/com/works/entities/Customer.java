package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@ApiModel(value = "Customer", description = "Yeni Müşteri Ekleme için Kullanılır.")
@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid", nullable = false)
    private Integer cid;

    @ApiModelProperty(value = "Müşteri İsmi", required = true, notes = "Müşteri İsmi belirlenmezse hata meydana gelir!")
    @NotNull(message = "Name parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Length(min = 2, max = 100, message = "Name min = 2, max = 100")
    private String cname;

    @ApiModelProperty(value = "Müşteri Soyismi", required = true, notes = "Soyisim belirlenmezse hata meydana gelir!")
    @NotNull(message = "Surname parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    private String csurname;

    private String ctel;
    private String ctel_2;

    @ApiModelProperty(value = "Müşteri Emaili", required = true, notes = "Email belirlenmezse hata meydana gelir!")
    @NotNull(message = "Email parametresi null olamaz!")
    @NotEmpty(message = "Bu alan boş olamaz!")
    @Email(message = "E-Mail formatı hatalı!")
    private String cmail;

    private String ctax_office;
    private String cnote;

    @ApiModelProperty(value = "Müşteri TC Kimlik Numarası", required = true, notes = "Belirlenmesi daha iyi bir seçenek")
    private Integer ccitizenship;

    @ApiModelProperty(value = "Müşteri İndirim Oranı", required = true, notes = "Belirlenmesi daha iyi bir seçenek")
    private Integer cdiscount;

    @ApiModelProperty(value = "Müşteri Adres Bilgileri", required = true, notes = "Belirlenmesi daha iyi bir seçenek")
    private String ccity;
    private String ctown;

    @ApiModelProperty(value = "Müşteri Adresi", required = true, notes = "Belirlenmesi daha iyi bir seçenek")
    private String caddress;
    private String cdate;

    @ApiModelProperty(value = "Müşteri Bakiyesi", required = true, notes = "Belirlenmesi daha iyi bir seçenek")
    private Integer cdebpt;



    @OneToMany(mappedBy = "cid", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Pet> pets;
}
