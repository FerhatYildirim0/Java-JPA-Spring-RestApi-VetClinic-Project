package com.works.restcontrollers;


import com.works.model.Agenda;
import com.works.model.AgendaRedis;
import com.works.redisrepositories.AgendaRedisRepository;
import com.works.repositories.AgendaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/agendaR")
public class AgendaRestController {

    final AgendaRepository agendaRepository;
    final AgendaRedisRepository agendaRedisRepository;

    public AgendaRestController(AgendaRepository agendaRepository, AgendaRedisRepository agendaRedisRepository) {
        this.agendaRepository = agendaRepository;
        this.agendaRedisRepository = agendaRedisRepository;
    }
    @ApiOperation(value = "Ajanda Ekleme (DB+Redis No Sql) Servisi")
    @PostMapping("/agadd")
    public Map<String, Object> agadd(@RequestBody Agenda agenda){
        Map<String, Object> hm = new LinkedHashMap<>();
        AgendaRedis aRediss = new AgendaRedis();
        aRediss.setAgdate(agenda.getAgdate());
        aRediss.setAgtext(agenda.getAgtext());
        aRediss.setAgtitle(agenda.getAgtitle());
        aRediss.setAgtime(agenda.getAgtime());
        String temp = UUID.randomUUID().toString();
        aRediss.setId(temp);
        AgendaRedis aRedis = agendaRedisRepository.save(aRediss);

        agenda.setId(temp);
        Agenda c = agendaRepository.save(agenda);

        hm.put("status", true);
        hm.put("result redis:", aRedis);
        hm.put("result DB:", c);
        return hm;
    }
    @ApiOperation(value = "Ajanda Listeleme Servisi")
    @GetMapping("/aglist")
    public Map<String, Object> aglist() {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<Agenda> ls = agendaRepository.findByOrderByAgidDesc();
        if (ls.size()>0) {
            hm.put("status", true);
            hm.put("result", ls);
         }
        else{
            hm.put("status", false);
            hm.put("message","Not Listeleme sırasında hata!");
        }
        return hm;
    }
    @ApiOperation(value = "Ajanda Silme (DB+Redis No Sql) Servisi")
    @GetMapping("/agdelete/{agdid}")
    public Map<String, Object> agdelete(@PathVariable String agdid){
        Map<String, Object> hm = new LinkedHashMap<>();
        agendaRedisRepository.deleteById(agdid);
        agendaRepository.deleteById(agdid);

        hm.put("status", true);
        hm.put("result", "Silme işlemi başarılı!");

        return hm;
    }

}
