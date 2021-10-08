package com.works.restcontrollers;

import com.works.dtos.SuppliersDto;
import com.works.entities.Suppliers;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/suppliersR")
public class SuppliersRestController {

    final SuppliersDto suppliersDto;

    public SuppliersRestController(SuppliersDto suppliersDto) {
        this.suppliersDto = suppliersDto;
    }

    @ApiOperation(value = "Tüm Tedarikçileri Listeleme Servisi")
    @GetMapping("/sulist")
    public Map<String, Object> sulist(){

        return suppliersDto.sulistdto();
    }

    @ApiOperation(value = "Tek Tedarikçiyi ID'ye göre getirme Servisi")
    @GetMapping("/suSingle/{supid}")
    public Map<String, Object> sulist(@PathVariable String supid){

        return suppliersDto.supSingledto(supid);
    }

    @ApiOperation(value = "Tedarikçi Ekleme veya Güncelleme Servisi")
    @PostMapping("/suaddUpdate")
    public Map<String, Object> suaddupdate(@RequestBody Suppliers suppliers){

        return suppliersDto.suaddUpdateDto(suppliers);
    }

    @ApiOperation(value = "Tedarikçi Silme Servisi")
    @DeleteMapping("/sudelete/{deleteid}")
    public Map<String, Object> sudelete(@PathVariable String deleteid){


        return suppliersDto.sudelete(deleteid);
    }

}
