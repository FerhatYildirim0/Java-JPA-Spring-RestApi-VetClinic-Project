package com.works.dtos;
import com.works.entities.Customer;
import com.works.entities.Lab;
import com.works.repositories.LabRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LabDto {

    final LabRepository labRepository;


    public LabDto(LabRepository labRepository) {
        this.labRepository = labRepository;
    }

    public Map<String, Object> labList(){
        Map<String, Object> hm = new LinkedHashMap<>();

        List<Lab> ls = labRepository.findByOrderByLidDesc();

        hm.put("status", true);
        hm.put("result", ls);


        return hm;

    }

}
