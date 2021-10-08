package com.works.restcontrollers;

import com.works.entities.Customer;
import com.works.entities.Pet;
import com.works.projections.CustomerSearchProjection;
import com.works.projections.PetCustomerProjection;
import com.works.repositories.CustomerRepository;
import com.works.repositories.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/customerR")
@Api( value = "Customer Rest Controller", authorizations = { @Authorization(value = "basicAuth")})
public class CustomerRestController {

    final CustomerRepository customerRepository;
    final PetRepository petRepository;

    public CustomerRestController(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;

    }
    @ApiOperation(value = "Tüm Müşterileri Son Eklenen En Üst Kısımda Çıkacak Şekilde Listeleme Servisi")
    @GetMapping("/culist")
    public Map<String, Object> culist(){
        Map<String, Object> hm = new LinkedHashMap<>();
        List<PetCustomerProjection> ls = customerRepository.findByAllIgnoreCaseOrderByCidDesc();
        hm.put("status", true);
        hm.put("result", ls);
      return hm;
    }
    @ApiOperation(value = "Tüm Müşterileri Listeleme Servisi")
    @GetMapping("/cuAllList")
    public Map<String, Object> cuAlllist(){
        Map<String, Object> hm = new LinkedHashMap<>();
        List<Customer> ls = customerRepository.findAll();
        hm.put("status", true);
        hm.put("result", ls);
        return hm;
    }

    @ApiOperation(value = "Müşteri Ekleme Servisi")
    @PostMapping("/cuadd")
    public Map<String, Object> cuadd(@RequestBody Customer customer){
        System.out.println(customer);


        Map<String, Object> hm = new LinkedHashMap<>();
        try {
            Customer customer1 = customerRepository.saveAndFlush(customer);
            hm.put("status", true);
            hm.put("result", customer1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return hm;
    }

    @ApiOperation(value = "Müşteri Silme Servisi")
   @GetMapping("/cudelete/{cuid}")
    public Map<String, Object> cudelete(@PathVariable String cuid){
         int cid= Integer.parseInt(cuid);
         Map<String, Object> hm = new LinkedHashMap<>();

            customerRepository.deleteById(cid);
            hm.put("status", true);
            hm.put("result", "Silme işlemi başarılı!");

        return hm;
    }

    @ApiOperation(value = "Evcil Hayvan Ekleme Servisi")
    @PostMapping("/cupetadd")
    public Map<String, Object> cupetadd(@RequestBody Pet pet){
        Map<String, Object> hm = new LinkedHashMap<>();
        Pet pet1 = petRepository.saveAndFlush(pet);
        System.out.println(pet1);
        hm.put("status", true);
        hm.put("result", pet1);

        return hm;
    }
    @ApiOperation(value = "İsme Müşteri Arama Servisi")
    @GetMapping("/cusearch/{item}")
    public Map<String, Object> cusearch(@PathVariable String item) {
        Map<String, Object> hm = new LinkedHashMap<>();
        List<CustomerSearchProjection> ls = customerRepository.findByCnameContains(item);

        if (ls.size() > 0){
            hm.put("status",true);
            hm.put("result",ls);
        }else {
            hm.put("status", false);
            hm.put("message", "Aranan isimde veya soyisimde müşteri bulunamadı");
        }

        return hm;
    }

    @ApiOperation(value = "Müşteri Listeleme Servisi (Pageable)")
    @GetMapping("/listp/{startPage}/{pageSize}")
    public Map<String ,Object> listCustomerP(@PathVariable String startPage,@PathVariable String pageSize) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int startpage=Integer.parseInt(startPage);
        int pagesize=Integer.parseInt(pageSize);

        Pageable pageable = PageRequest.of(startpage, pagesize);
        List<Customer> ls=  customerRepository.findByOrderByCidAsc(pageable);
        if (ls.size() != 0) {
            long totalCustomer= customerRepository.countByCidAllIgnoreCase();
            if(startpage ==0){
                startpage = 1;
            }
            hm.put("status", true);
            hm.put("message :", "Sayfa sayısı : " + totalCustomer/pagesize   + "  Listelenen müşteri :  " + startpage*pagesize);
            hm.put("Toplam müşteri :", totalCustomer);
            hm.put("result", ls);
        } else {

            hm.put("status", false);
            hm.put("message", "Veritabanında yeterli miktarda veri yok!");
        }


        return hm;
    }
    @ApiOperation(value = "Müşteri Güncelleme Servisi")
    @PutMapping("/cuupdate/{upindex}")
    public Map<String, Object> cuupdate(@RequestBody Customer customer, @PathVariable String upindex) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int cid=Integer.parseInt(upindex);
        Customer customerOld = new Customer();
        customerOld = customerRepository.findById(cid).get();
        customerOld = customer;
        try {
            customerRepository.saveAndFlush(customerOld);
                hm.put("status",true);
                hm.put("result",customer);

        } catch (Exception e) {
            System.err.println("Müşteri güncelleme sırasında hata " + e);
        }
        return hm;
    }
    @ApiOperation(value = "ID'ye göre müşteri arama servisi")
    @GetMapping("/cusearchS/{id}")
    public Map<String, Object> cusinglesearch(@PathVariable String id) {
        Map<String, Object> hm = new LinkedHashMap<>();
        int cid=Integer.parseInt(id);

        try {
            Optional<CustomerSearchProjection> customerSearchProjection = customerRepository.findByCidIs(cid);

            if (customerSearchProjection.isPresent()){
                hm.put("status",true);
                hm.put("result",customerSearchProjection);

            }else {
                hm.put("status",false);
            }
        } catch (Exception e) {
            System.err.println("Arama sırasında hata " + e);
        }

        return hm;
    }
}
