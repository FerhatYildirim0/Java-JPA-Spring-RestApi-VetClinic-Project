package com.works.restcontrollers;

import com.works.dtos.VaccineDto;
import com.works.entities.Product;
import com.works.entities.Vaccine;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vaccineR")
public class VaccineRestController {

    final VaccineDto vaccineDto;

    public VaccineRestController(VaccineDto vaccineDto) {
        this.vaccineDto = vaccineDto;
    }

    @ApiOperation(value = "Tüm Aşıları Listeleme Servisi")
    @GetMapping("/valist")
    public Map<String, Object> valist(){
        return vaccineDto.valistdto();
    }

    @ApiOperation(value = "Aktif Aşıları Listeleme Servisi")
    @GetMapping("/prActiveCount")
    public long vaActiveList(){
        return vaccineDto.vaActiveListdto();
    }

    @ApiOperation(value = "Aşı Ekleme veya Güncelleme Servisi")
    @PostMapping("/vaaddUpdate")
    public Map<String, Object> vaaddupdate(@RequestBody Vaccine vaccine){
        return vaccineDto.vaaddUpdate(vaccine);
    }

    @ApiOperation(value = "Aşı Silme Servisi")
    @DeleteMapping("/vadelete/{vadeleteid}")
    public Map<String, Object> vadelete(@PathVariable String vadeleteid){


        return vaccineDto.vadelete(vadeleteid);
    }
    @ApiOperation(value = "ID'ye Göre Tek Aşı Getirme Servisi")
    @GetMapping("/singleVac/{svacid}")
    public Map<String, Object> singleVac(@PathVariable String svacid){
        return vaccineDto.vacSingledto(svacid);
    }
}
