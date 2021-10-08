package com.works.restcontrollers;

import com.works.dtos.SaleDto;
import com.works.entities.Sale;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sale")
public class SaleRestController {
    final SaleDto saleDto;
    public SaleRestController(SaleDto saleDto) {
        this.saleDto = saleDto;
    }

    @ApiOperation(value = "Satışı Onaylama Servisi")
    @GetMapping("/saleStatus/{sindex}")
    public Map<String, Object> saStatus(@PathVariable String sindex) {

        return saleDto.statusChange(sindex);
    }

    @ApiOperation(value = "Satışı Silme Servisi")
    @GetMapping("/deleteSale/{index}")
    public Map<String, Object> deleteSale(@PathVariable String index) {

        return saleDto.saDelete(index);
    }

    @ApiOperation(value = "Satış Ekleme Servisi")
    @PostMapping("/saleinsert")
    public Map<String, Object> saInsert(@RequestBody Sale sale) {

        return  saleDto.sainsert(sale);
    }

}
