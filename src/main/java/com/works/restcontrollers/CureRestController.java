package com.works.restcontrollers;

import com.works.dtos.CureDto;
import com.works.entities.Cure;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cure")
public class CureRestController {

    final CureDto cureDto;
    public CureRestController(CureDto cureDto) {
        this.cureDto = cureDto;
    }



    @ApiOperation(value = "Tedavileri Ekleme Servisi")
    @PostMapping("/add")
    public Map<String, Object> addCure(@RequestBody Cure cure) {

        return  cureDto.addCure(cure);
    }

    @ApiOperation(value = "Tedavileri Ekleme veya Güncelleme Servisi")
    @PostMapping("/addAndUpdate")
    public Map<String, Object> addAndUpdateCure(@RequestBody Cure cure) {

        return  cureDto.addUpdateCure(cure);
    }

    @ApiOperation(value = "Tüm Tedavileri Listeleme Servisi")
    @GetMapping("/listAllCure")
    public Map<String, Object> listAllCure() {

        return cureDto.listAllCure();
    }

    @ApiOperation(value = "Tek Tedavi Gösterme Servisi")
    @GetMapping("/singleCure/{index}")
    public Map<String, Object> sngleCure(@PathVariable String index) {

        return cureDto.singleCure(index);
    }

    @ApiOperation(value = "Tedavi Silme Servisi")
    @GetMapping("/deleteCure/{index}")
    public Map<String, Object> deleteCure(@PathVariable String index) {

        return cureDto.deleteCure(index);
    }




}
