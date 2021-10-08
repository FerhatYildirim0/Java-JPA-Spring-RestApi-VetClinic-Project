package com.works.restcontrollers;

import com.works.dtos.BuyingDto;
import com.works.entities.Buying;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/buying")
public class BuyingRestController {

    final BuyingDto buyingDto;

    public BuyingRestController(BuyingDto buyingDto) {
        this.buyingDto = buyingDto;
    }
    @ApiOperation(value = "Satın Alım Ekleme Servisi")
    @PostMapping("/add")
    public Map<String,Object> buyingInsert(@RequestBody Buying buying){

        return buyingDto.buinsert(buying);
    }
    @ApiOperation(value = "Satın Alımı Onaylama Servisi")
    @GetMapping("/statusChange/{index}")
    public Map<String, Object> buyingStatusChange(@PathVariable String index) {

        return buyingDto.buStatusChange(index);
    }
    @ApiOperation(value = "Satın Alımın Detaylarını Gösterme Servisi")
    @GetMapping("/buyingDetail/{index}")
    public Map<String, Object> buyingDetail(@PathVariable String index) {

        return buyingDto.buDetail(index);
    }
    @ApiOperation(value = "Satın Alımı Silme Servisi")
    //id ye göre sepettekini siler
    @GetMapping("/deleteBuying/{index}")
    public Map<String, Object> deletebuying(@PathVariable String index) {

        return buyingDto.buDelete(index);
    }
    @ApiOperation(value = "Sepet Satın Alımı Silme Servisi")
    //Bidx'e id gelmeli. Arka planda id den obje, objeden fatura no bulup o fatura noya ait tüm objeler siliniyor.
    @GetMapping("/deleteListBuying/{bidx}")
    public Map<String, Object> deleteListbuying(@PathVariable String bidx) {

        return buyingDto.buListDelete(bidx);
    }

}
