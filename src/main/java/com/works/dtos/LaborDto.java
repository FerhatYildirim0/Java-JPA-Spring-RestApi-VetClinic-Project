package com.works.dtos;
import com.works.entities.Labor;
import com.works.entities.Pet;
import com.works.repositories.LaborRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LaborDto {
    final LaborRepository laborRepository;

    public LaborDto(LaborRepository laborRepository) {
        this.laborRepository = laborRepository;
    }
    public Map<String, Object> laborAdd(Labor labor){
        Map<String, Object> hm = new LinkedHashMap();
        try{
            System.out.println("DTO Tarafı :  ============================================ " + labor.getLaname());
            if (labor.getLaname()!=null){
                Labor labor1 = laborRepository.saveAndFlush(labor);
                hm.put("status",true);
                hm.put("result", labor1);
            }
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Hizmet ekleme sırasında bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> lalistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<Labor> ls = laborRepository.findAll();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Hizmetler listelenirken bir hata meydana geldi");
        }

        return hm;
    }

    public Map<String, Object> laupdate(Labor labor, String upindex) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int laid=Integer.parseInt(upindex);

        System.out.println("DTO'daki indexx ============================================================> " + laid);
        Labor laborOld = new Labor();
        laborOld = laborRepository.findById(laid).get();
        laborOld = labor;
        laborOld.setLaid(laid);
        System.out.println("DTO'da son aşama             " +laborOld.getLaid()+ "         "  + laborOld.getLaname());
        try {
            laborRepository.saveAndFlush(laborOld);
            hm.put("status",true);
            hm.put("result",labor);

        } catch (Exception e) {
            System.err.println("Hizmet güncelleme sırasında hata " + e);
        }
        return hm;
    }

    public Map<String, Object> labSingledto(String slaborid){
        Map<String, Object> hm = new LinkedHashMap();
        int laid=Integer.parseInt(slaborid);

        Optional<Labor> labor = laborRepository.findById(laid);
        if(labor.isPresent()){
            hm.put("status", true);
            hm.put("result", labor);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Hizmet bilgileri güncellenirken bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> ladelete(String ladeleteid){
        int laid = Integer.parseInt(ladeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            laborRepository.deleteById(laid);
            hm.put("status",true);
            hm.put("result", "Hizmet silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Hizmet silme sırasında bir hata meydana geldi");

        }
        return hm;
    }

}
