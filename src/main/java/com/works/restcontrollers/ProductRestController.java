package com.works.restcontrollers;

import com.works.dtos.ProductDto;
import com.works.entities.Product;
import com.works.entities.Suppliers;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/productR")
public class ProductRestController {

    final ProductDto productDto;
    public ProductRestController(ProductDto productDto) {
        this.productDto = productDto;
    }

    @ApiOperation(value = "Tüm Ürünleri Listeleme Servisi")
    @GetMapping("/prlist")
    public Map<String, Object> prlist(){
        return productDto.prlistdto();
    }

    @ApiOperation(value = "ID'ye göre tek bir ürün getirme Servisi")
    @GetMapping("/prsingle/{proid}")
    public Map<String, Object> prSingleR(@PathVariable String proid){
        return productDto.prSingle(proid);
    }

    @ApiOperation(value = "Ürün Ekleme veya güncelleme Servisi")
    @PostMapping("/praddUpdate")
    public Map<String, Object> praddupdate(@RequestBody Product product){
        return productDto.praddUpdate(product);
    }

    @ApiOperation(value = "Ürün Silme Servisi")
    @DeleteMapping("/prdelete/{prdeleteid}")
    public Map<String, Object> sudelete(@PathVariable String prdeleteid){


        return productDto.prdelete(prdeleteid);
    }


}
