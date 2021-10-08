package com.works.restcontrollers;

import com.works.dtos.LaborDto;
import com.works.entities.Labor;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/labor")
public class LaborRestController {
    final LaborDto laborDto;

    public LaborRestController(LaborDto laborDto) {
        this.laborDto = laborDto;
    }

    @ApiOperation(value = "Hizmet Ekleme Servisi")
    @PostMapping("/add")
    public Map<String, Object> labAdd(@RequestBody Labor labor){

        return laborDto.laborAdd(labor);
    }
    @ApiOperation(value = "Hizmet Listeleme Servisi")
    @GetMapping("/list")
    public Map<String, Object> labList(){

        return laborDto.lalistdto();
    }
    @ApiOperation(value = "Hizmet Silme Servisi")
    @DeleteMapping("/delete/{ladeleteid}")
    public Map<String, Object> laDelete(@PathVariable String ladeleteid){

        return laborDto.ladelete(ladeleteid);
    }
    @ApiOperation(value = "Hizmet Güncelleme Servisi")
    @PutMapping("/update/{upindex}")
    public Map<String, Object> laUpdate(@RequestBody Labor labor, @PathVariable String upindex){

        return laborDto.laupdate(labor, upindex);
    }
    @ApiOperation(value = "ID'ye Tek Hizmet Getirme Servisi")
    @GetMapping("/singleLabor/{slaborid}")
    public Map<String, Object> singleLabor(@PathVariable String slaborid){
        return laborDto.labSingledto(slaborid);
    }


}
