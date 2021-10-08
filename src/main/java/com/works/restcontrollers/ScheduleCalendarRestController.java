package com.works.restcontrollers;

import com.works.dtos.ScheduleCalendarDto;
import com.works.entities.ScheduleCalendar;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/calendar")
public class ScheduleCalendarRestController {

    final ScheduleCalendarDto scheduleCalendarDto;

    public ScheduleCalendarRestController(ScheduleCalendarDto scheduleCalendarDto) {
        this.scheduleCalendarDto = scheduleCalendarDto;
    }

    @ApiOperation(value = "Takvime Randevu Ekleme Servisi")
    @PostMapping("/addSchedule")
    public Map<String, Object> caAdd(@RequestBody ScheduleCalendar scheduleCalendar){

        return scheduleCalendarDto.addSchedule(scheduleCalendar);
    }

    @ApiOperation(value = "Takvimde Tüm Randevuları Listeleme Servisi")
    @GetMapping("/listSchedule/{calendarId}")
    public Map<String, Object>  caListSchedule(@PathVariable String calendarId){

        return scheduleCalendarDto.listSchedule(calendarId);
    }

    @ApiOperation(value = "Takvimden Randevu Silme Servisi")
    @DeleteMapping("/deleteSchedule/{cadeleteid}")
    public Boolean caDelete(@PathVariable String cadeleteid){

        return scheduleCalendarDto.deleteSchedule(cadeleteid);
    }

}
