package com.works.restcontrollers;

import com.works.dtos.MoneyCaseDto;
import com.works.entities.MoneyCase;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/case")
public class MoneyCaseRestController {

    final MoneyCaseDto moneyCaseDto;

    public MoneyCaseRestController(MoneyCaseDto moneyCaseDto) {
        this.moneyCaseDto = moneyCaseDto;
    }
    @ApiOperation(value = "Tüm Kasa Hareketlerini Listeleme Servisi")
    @GetMapping("/list")
    public Map<String, Object> calist(){

        return moneyCaseDto.moneyList();
    }
    @ApiOperation(value = "Kasaya Para Giriş-Çıkışı Ekleme Servisi")
    @PostMapping("/add")
    public Map<String, Object> caadd(@RequestBody MoneyCase moneyCase){

        return moneyCaseDto.addCase(moneyCase);
    }
    @ApiOperation(value = "Kasaya Para Giriş-Çıkışı Silme Servisi")
    @PostMapping("/remove")
    public Map<String, Object> caremove(@RequestBody MoneyCase moneyCase){

        return moneyCaseDto.removeCase(moneyCase);
    }

}
