package com.works.dtos;
import com.works.entities.Pet;
import com.works.entities.Product;
import com.works.entities.Vaccine;
import com.works.repositories.VaccineRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VaccineDto {

    final VaccineRepository vaccineRepository;

    public VaccineDto(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public Map<String, Object> valistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<Vaccine> ls = vaccineRepository.findAll();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Aşı listelenirken bir hata meydana geldi");
        }
        return hm;
    }

    public long vaActiveListdto(){

        Map<String, Object> hm = new LinkedHashMap();
       long ActiveCount = vaccineRepository.countByVacactiveEquals(1);
        if(ActiveCount > 0){
            hm.put("status", true);
            hm.put("result", ActiveCount);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Aktif Aşılar listelenirken bir hata meydana geldi");
        }
        return ActiveCount;
    }

    public Map<String, Object> vaaddUpdate(Vaccine vaccine){
        Map<String, Object> hm = new LinkedHashMap();
        try{
            //    System.out.println("DTO Tarafı :  ============================================ " + suppliers.getSuname());
            if (vaccine.getVacname()!=null){
                Vaccine vaccine1 = vaccineRepository.saveAndFlush(vaccine);
                hm.put("status",true);
                hm.put("result", vaccine1);
            }
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Aşı ekleme sırasında bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> vadelete(String vadeleteid){
        int vacid = Integer.parseInt(vadeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            vaccineRepository.deleteById(vacid);
            hm.put("status",true);
            hm.put("result", "Ürün silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Aşı silme sırasında bir hata meydana geldi");

        }
        return hm;
    }
    public Map<String, Object> vacSingledto(String svacid){
        Map<String, Object> hm = new LinkedHashMap();
        int vacid=Integer.parseInt(svacid);

        Optional<Vaccine> vaccine = vaccineRepository.findById(vacid);
        if(vaccine.isPresent()){
            hm.put("status", true);
            hm.put("result", vaccine);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Evcil Hayvan bilgileri yüklenirken bir hata meydana geldi");
        }
        return hm;
    }

}
