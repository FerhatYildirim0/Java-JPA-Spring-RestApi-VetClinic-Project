package com.works.dtos;


import com.works.entities.MoneyCase;
import com.works.entities.Product;
import com.works.repositories.CaseRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyCaseDto {
    final CaseRepository caseRepository;

    public MoneyCaseDto(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public Map<String, Object> moneyList(){
        Map<String, Object> hm = new LinkedHashMap();

        List<MoneyCase> ls = caseRepository.findByOrderByCidDesc();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Kasa Bilgileri listelenirken bir hata meydana geldi");
        }
        return hm;
    }

    //Buy Ekleme  POST MAPPING
    public Map<String, Object> addCase(MoneyCase moneyCase){
        Map<String, Object> hm = new LinkedHashMap();
//        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
//        String email = aut.getName(); // username

        moneyCase.setCbuysell(1);
       // moneyCase.setCname(email);
        caseRepository.save(moneyCase);
        hm.put("status",true);
        hm.put("result", moneyCase);

        return hm;
    }
   //Buy çıkarma POST

    public Map<String, Object> removeCase(MoneyCase moneyCase) {
        Map<String, Object> hm = new LinkedHashMap();
//        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
//        String email = aut.getName(); // username

        moneyCase.setCbuysell(2);
//        moneyCase.setCname(email);
        caseRepository.save(moneyCase);
        hm.put("status",true);
        hm.put("result", moneyCase);
        return hm;
    }

}
