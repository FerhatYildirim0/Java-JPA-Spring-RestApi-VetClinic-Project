package com.works.dtos;
import com.works.entities.Customer;
import com.works.entities.Pet;
import com.works.entities.Vaccine;
import com.works.projections.PetProjection;
import com.works.repositories.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PetDto {

    final PetRepository petRepository;

    public PetDto(PetRepository petRepository) {
        this.petRepository = petRepository;

    }

    public Map<String, Object> petlistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<Pet> ls = petRepository.findAll();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Evcil Hayvanlar listelenirken bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> petaddUpdate(Pet pet){
        Map<String, Object> hm = new LinkedHashMap();
        try{
             System.out.println("PET DTO Tarafı :  ============================================> " + pet.getPname());

                Pet pet1 = petRepository.saveAndFlush(pet);
                hm.put("status",true);
                hm.put("result", pet1);

        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Evcil Hayvan ekleme sırasında bir hata meydana geldi :   " + ex);
        }
        return hm;
    }
    public Map<String, Object> petdelete(String petdeleteid){
        int pid = Integer.parseInt(petdeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            petRepository.deleteById(pid);
            hm.put("status",true);
            hm.put("result", "Evcil Hayvan silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Evcil Hayvan silme sırasında bir hata meydana geldi");

        }
        return hm;
    }


    public Map<String, Object> petupdate(Pet pet, String upindex) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int pid=Integer.parseInt(upindex);

        Pet petOld = new Pet();
        petOld = petRepository.findById(pid).get();
        petOld = pet;
        petOld.setPid(pid);
        try {
            petRepository.saveAndFlush(petOld);
            hm.put("status",true);
            hm.put("result",pet);

        } catch (Exception e) {
            System.err.println("Pet güncelleme sırasında hata " + e);
        }
        return hm;
    }
    public Map<String, Object> petSingledto(String spid){
        Map<String, Object> hm = new LinkedHashMap();
        int pid=Integer.parseInt(spid);

        Optional<Pet> pet = petRepository.findById(pid);
        try {
            if(pet.isPresent()){
                hm.put("status", true);
                hm.put("result", pet);
            }

            else{
                hm.put("status", false);
                hm.put("message", "Evcil Hayvan bilgileri yüklenirken bir hata meydana geldi");
            }
        } catch (Exception e) {
            hm.put("error", "Evcil Hayvan bilgileri yüklenirken bir hata meydana geldi : " + e);
        }


        return hm;
    }
}
