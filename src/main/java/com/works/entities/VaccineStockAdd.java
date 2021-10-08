package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@ApiModel(value = "VaccineStockAdd", description = "Yeni Stok eklemek için kullanılan property")
@Getter
@Setter
public class VaccineStockAdd {

    @ApiModelProperty(value = "Aşı ID'si ")
    private Integer vac_id;
    private Integer vac_select;

    @ApiModelProperty(value = "Aşı Miktarı ")
    private Integer vac_amount;

}
