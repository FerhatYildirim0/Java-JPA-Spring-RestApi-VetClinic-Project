package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@ApiModel(value = "ProductStockAdd", description = "Yeni Stok eklemek için kullanılan property")
@Getter
@Setter
public class ProductStockAdd {

    @ApiModelProperty(value = "Ürün ID'si ")
    private Integer pr_id;
    private Integer pr_select;

    @ApiModelProperty(value = "Ürün Miktarı ")
    private Integer pr_amount;

}
