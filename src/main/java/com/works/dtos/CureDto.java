package com.works.dtos;

import com.works.entities.Cure;
import com.works.entities.Vaccine;
import com.works.repositories.CureRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CureDto {

    final CureRepository cureRepository;

    public CureDto(CureRepository cureRepository) {
        this.cureRepository = cureRepository;
    }

    public Map<String, Object> addCure(Cure cure) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            Cure cure1 =  cureRepository.saveAndFlush(cure);
            hm.put("status", true);
            hm.put("result", cure1);
            hm.put("message","Tedavi Kaydetme başarılı ");
        } catch (Exception ex) {
            hm.put("status", false);
            hm.put("message","Tedavi Kaydetme sırasında hata "+ex);
        }

        return hm;
    }



    public Map<String, Object> listAllCure() {
        Map<String, Object> hm = new LinkedHashMap<>();

        try {
            List<Cure> ls =cureRepository.findAll();
            hm.put("status", true);
            hm.put("result", ls);
        } catch (Exception ex) {
            hm.put("status", false);
            hm.put("message","Tedavi listesi yüklenirken hata "+ex);
        }
        return hm;
    }


    public Map<String, Object> singleCure(String index) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int id=Integer.parseInt(index);

        try {
            Optional<Cure> cure= cureRepository.findById(id);
            if (cure.isPresent()){
                hm.put("status", true);
                hm.put("result", cure);

            }else {
                hm.put("status", false);
                hm.put("message", "Bu id'ye sahip bir Tedavi yok : "+id);
            }
        } catch (Exception ex) {
            hm.put("message","Tek Tedavi aranırken hata " + ex);
        }

        return hm;
    }

    public Map<String, Object> deleteCure(String index) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int id= Integer.parseInt(index);

        try {
            cureRepository.deleteById(id);
            hm.put("status", true);
            hm.put("message", "Tedavi silme başarılı");
        } catch (Exception ex) {
            hm.put("result", false);
            hm.put("message", "Tedavi silme işlemi sırasında hata : "+ ex);
        }

        return hm;
    }



    public Map<String, Object> addUpdateCure(Cure cure) {
        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            if (cure.getCurid() !=null){
                Cure cure1=  cureRepository.saveAndFlush(cure);
                hm.put("status", true);
                hm.put("result", cure1);

            }else{
                Cure cure1=  cureRepository.saveAndFlush(cure);
                hm.put("status", true);
                hm.put("result", cure1);
            }

        } catch (Exception ex) {

            hm.put("status", false);
            hm.put("message", "Tedavi kaydetme sırasında hata:  " + ex);

        }
        return hm;
    }






}
