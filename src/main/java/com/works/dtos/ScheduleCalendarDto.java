package com.works.dtos;
import com.works.entities.ScheduleCalendar;
import com.works.repositories.CalendarInfoRepository;
import com.works.repositories.ScheduleCalendarRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ScheduleCalendarDto {

    final ScheduleCalendarRepository scheduleCalendarRepository;
    final CalendarInfoRepository calendarInfoRepository;
    public ScheduleCalendarDto(ScheduleCalendarRepository scheduleCalendarRepository, CalendarInfoRepository calendarInfoRepository) {
        this.scheduleCalendarRepository = scheduleCalendarRepository;
        this.calendarInfoRepository = calendarInfoRepository;
    }

    //GETMAPPING
    public Map<String, Object> calendarInfo() {
        Map<String, Object> hm = new LinkedHashMap<>();

        hm.put("status", true);
        hm.put("result", calendarInfoRepository.findAll());
        return hm;

    }
    //POSTMAPPING
    public Map<String, Object> addSchedule(@RequestBody ScheduleCalendar scheduleCalendar) {
        Map<String, Object> hm = new LinkedHashMap<>();

        Optional<ScheduleCalendar> isThere = scheduleCalendarRepository.findScheduleId(scheduleCalendar.getId());
        if (isThere.isPresent()) {
            scheduleCalendar.setSid(isThere.get().getSid());
        }
        ScheduleCalendar scheduleCalendar1 = scheduleCalendarRepository.saveAndFlush(scheduleCalendar);
        hm.put("status",true);
        hm.put("result", scheduleCalendar1);
        return hm;
    }


    public Map<String, Object> listSchedule(String calendarId) {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<ScheduleCalendar> ls = scheduleCalendarRepository.findByCalendarIdEquals(calendarId);
        System.out.println(" ========================================================================" + ls.size());
        hm.put("status", true);
        hm.put("result", ls);
        return hm;
    }



    public Boolean deleteSchedule(String cadeleteid) {
        Optional<ScheduleCalendar> isThere = scheduleCalendarRepository.findScheduleId(cadeleteid);

        if (isThere.isPresent()) {
            scheduleCalendarRepository.deleteById(isThere.get().getSid());
            System.out.println(isThere.get().getSid());
            return true;
        } else {

            return false;
        }
    }

}
