package com.works.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@ApiModel(value = "Cure", description = "Yeni Tedavi Eklemek için Kullanılır.")
@Entity
@Getter
@Setter
@JsonSerialize

public class Cure {
    @Id
    @Column(name = "curid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer curid;

    @ApiModelProperty(value = "Tedavi Başlığı")
    private String curtitle;
    @ApiModelProperty(value = "Tedavi Yorumu")
    private String curcomment;
    @ApiModelProperty(value = "Tedavi İçin Toplam Borç")
    private Integer curdebt;

    @OneToOne
    private  Pet pet;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curvac")
    private Vaccine curvac;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curpro")
    private Product curpro;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curla")
    private Labor curla;


}
