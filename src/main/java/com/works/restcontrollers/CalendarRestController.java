package com.works.restcontrollers;

import com.works.dtos.CalendarInfoDto;
import com.works.entities.CalendarInfo;
import com.works.entities.Suppliers;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/calendarR")
public class CalendarRestController {

    final CalendarInfoDto calendarInfoDto;

    public CalendarRestController(CalendarInfoDto calendarInfoDto) {
        this.calendarInfoDto = calendarInfoDto;
    }

    @ApiOperation(value = "Takvim Listeleme Servisi")
    @GetMapping("/calist")
    public Map<String, Object> calist(){

        return calendarInfoDto.calistdto();
    }
    @ApiOperation(value = "Takvim Ekleme Servisi")
    @PostMapping("/caaddUpdate")
    public Map<String, Object> caaddupdate(@RequestBody CalendarInfo calendarInfo){

        return calendarInfoDto.caaddUpdateDto(calendarInfo);
    }

    @ApiOperation(value = "Takvim Silme Servisi")
    @DeleteMapping("/cadelete/{cadeleteid}")
    public Map<String, Object> cadelete(@PathVariable String cadeleteid){


        return calendarInfoDto.cadelete(cadeleteid);
    }

}
