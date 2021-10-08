package com.works.dtos;
import com.works.entities.Suppliers;
import com.works.entities.Vaccine;
import com.works.repositories.SuppliersRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SuppliersDto {

    final SuppliersRepository suppliersRepository;

    public SuppliersDto(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }


    public Map<String, Object> sulistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<Suppliers> ls = suppliersRepository.findAll();
        if(ls.size() > 0){
        hm.put("status", true);
        hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Tedarikçi listelenirken bir hata meydana geldi");
        }

        return hm;
    }

    public Map<String, Object> suaddUpdateDto(Suppliers suppliers){
        Map<String, Object> hm = new LinkedHashMap();
        try{
            System.out.println("DTO Tarafı :  ============================================ " + suppliers.getSuname());
            if (suppliers.getSuname()!=null){
                Suppliers suppliers1 = suppliersRepository.saveAndFlush(suppliers);
                hm.put("status",true);
                hm.put("result", suppliers1);
            }
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Tedarikçi ekleme sırasında bir hata meydana geldi");
        }
        return hm;
    }
    public Map<String, Object> sudelete(String deleteid){
        int suid = Integer.parseInt(deleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            suppliersRepository.deleteById(suid);
            hm.put("status",true);
            hm.put("result", "Tedarikçi silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Tedarikçi silme sırasında bir hata meydana geldi");

        }
        return hm;
    }
    public Map<String, Object> supSingledto(String supid){
        Map<String, Object> hm = new LinkedHashMap();
        int suid=Integer.parseInt(supid);

        Optional<Suppliers> suppliers = suppliersRepository.findById(suid);
        if(suppliers.isPresent()){
            hm.put("status", true);
            hm.put("result", suppliers);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Tedarikçi bilgileri güncellenirken bir hata meydana geldi");
        }
        return hm;
    }
}
