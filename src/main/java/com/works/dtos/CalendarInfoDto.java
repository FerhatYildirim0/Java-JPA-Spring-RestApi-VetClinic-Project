package com.works.dtos;

import com.works.entities.CalendarInfo;
import com.works.entities.Suppliers;
import com.works.repositories.CalendarInfoRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarInfoDto {

    final CalendarInfoRepository calendarInfoRepository;

    public CalendarInfoDto(CalendarInfoRepository calendarInfoRepository) {
        this.calendarInfoRepository = calendarInfoRepository;
    }

    public Map<String, Object> calistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<CalendarInfo> ls = calendarInfoRepository.findAll();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Takvim Bilgisi listelenirken bir hata meydana geldi");
        }

        return hm;
    }

    public Map<String, Object> caaddUpdateDto(CalendarInfo calendarInfo){
        Map<String, Object> hm = new LinkedHashMap();
        try{
            System.out.println("DTO Tarafı :  ============================================ " + calendarInfo.getCname());
            if (calendarInfo.getCname()!=null){
                CalendarInfo calendarInfo1 = calendarInfoRepository.saveAndFlush(calendarInfo);
                hm.put("status",true);
                hm.put("result", calendarInfo1);
            }
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Takvim Bilgisi ekleme sırasında bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> cadelete(String cadeleteid){
        int cid = Integer.parseInt(cadeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            calendarInfoRepository.deleteById(cid);
            hm.put("status",true);
            hm.put("result", "Takvim Bilgisi silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Takvim Bilgisi silme sırasında bir hata meydana geldi");

        }
        return hm;
    }

}
