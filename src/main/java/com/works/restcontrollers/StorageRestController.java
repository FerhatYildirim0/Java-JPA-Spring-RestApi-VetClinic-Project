package com.works.restcontrollers;

import com.works.dtos.StorageDto;
import com.works.entities.Storage;
import com.works.entities.Suppliers;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/storageR")
public class StorageRestController {

    final StorageDto storageDto;

    public StorageRestController(StorageDto storageDto) {
        this.storageDto = storageDto;
    }

    @ApiOperation(value = "Tüm Stok Aktivitelerini Listeleme Servisi")
    @GetMapping("/stlist")
    public Map<String, Object> stlist(){

        return storageDto.stlistdto();
    }




//    @PostMapping("/staddUpdate")
//    public Map<String, Object> staddupdate(@RequestBody Storage storage){
//
//        return storageDto.staddUpdateDto(storage);
//    }


    @ApiOperation(value = "Stok Aktivitesini Silme Servisi")
    @DeleteMapping("/stdelete/{stdeleteid}")
    public Map<String, Object> stdelete(@PathVariable String stdeleteid){


        return storageDto.stdelete(stdeleteid);
    }
}
