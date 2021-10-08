package com.works.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ApiModel(value = "Labor", description = "Yeni Hizmet Eklemek için Kullanılır.")
public class Labor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "laid", nullable = false)
    private Integer laid;

    @ApiModelProperty(value = "Hizmet İsmi")
    private String laname;
    @ApiModelProperty(value = "Hizmet Fiyatı")
    private Integer lamoney;
}
