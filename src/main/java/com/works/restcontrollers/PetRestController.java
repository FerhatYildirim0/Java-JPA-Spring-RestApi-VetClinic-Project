package com.works.restcontrollers;

import com.works.dtos.PetDto;
import com.works.entities.Pet;
import com.works.entities.Product;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/petR")
public class PetRestController {
    final PetDto petDto;

    public PetRestController(PetDto petDto) {
        this.petDto = petDto;
    }

    @ApiOperation(value = "Tüm Evcil Hayvanları Listeleme Servisi")
    @GetMapping("/petlist")
    public Map<String, Object> petlist(){
        return petDto.petlistdto();
    }

    @ApiOperation(value = "Tek Evcil Hayvanı ID'ye göre getirme Servisi")
    @GetMapping("/petSingle/{spid}")
    public Map<String, Object> petSingle(@PathVariable String spid){
        return petDto.petSingledto(spid);
    }

    @ApiOperation(value = "Evcil Hayvan Ekleme ve Güncelleme Servisi (Validation)")
    @PostMapping("/petaddUpdate")
    public Map<String, Object> petaddupdate(@RequestBody @Valid Pet pet, BindingResult bindingResult){
        Map<String,Object> hm =new LinkedHashMap<>();
        if ( bindingResult.hasErrors() ) {
            hm.put("status", false);
            hm.put("message", errors(bindingResult));
        }else {
            petDto.petaddUpdate(pet);
        }
        return hm;
    }
    @ApiOperation(value = "Evcil Hayvan Silme Servisi")
    @DeleteMapping("/petdelete/{petdeleteid}")
    public Map<String, Object> sudelete(@PathVariable String petdeleteid){
        return petDto.petdelete(petdeleteid);
    }

    @ApiOperation(value = "Evcil Hayvan Güncelleme Servisi")
    @PutMapping("/petupdate/{upindex}")
    public Map<String, Object> petUpdate(@RequestBody Pet pet, @PathVariable String upindex){
        return petDto.petupdate(pet, upindex);
    }


    public List<Map<String, String>> errors(BindingResult bindingResult) {
        List<Map<String, String>> ls = new LinkedList<>();

        bindingResult.getAllErrors().forEach( error -> {
            String fieldName = ( (FieldError) error ).getField();
            String fieldMessage = error.getDefaultMessage();

            Map<String,String> erhm = new HashMap<>();
            erhm.put("fieldName", fieldName);
            erhm.put("fieldMessage", fieldMessage);
            ls.add(erhm);
        });

        return ls;
    }
}
