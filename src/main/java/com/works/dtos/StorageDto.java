package com.works.dtos;
import com.works.entities.Storage;
import com.works.entities.Suppliers;
import com.works.repositories.StorageRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageDto {

    final StorageRepository storageRepository;


    public StorageDto(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public Map<String, Object> stlistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<Storage> ls = storageRepository.findByOrderByStidDesc();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Stok Aktiviteleri listelenirken bir hata meydana geldi");
        }

        return hm;
    }

//    public Map<String, Object> staddUpdateDto(Storage storage){
//        Map<String, Object> hm = new LinkedHashMap();
//        try{
//            System.out.println("DTO Tarafı :  ============================================ " + storage.getStid());
//            if (storage.getStid() > 0){
//                Storage storage1 = storageRepository.saveAndFlush(storage);
//                hm.put("status",true);
//                hm.put("result", storage1);
//            }
//        }
//        catch (Exception ex){
//            hm.put("status", false);
//            hm.put("message", "Stok Aktivitesi ekleme sırasında bir hata meydana geldi");
//        }
//        return hm;
//    }

    public Map<String, Object> stdelete(String stdeleteid){
        int stid = Integer.parseInt(stdeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            storageRepository.deleteById(stid);
            hm.put("status",true);
            hm.put("result", "Stok Aktivitesi silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Stok Aktivitesi silme sırasında bir hata meydana geldi");

        }
        return hm;
    }

}
