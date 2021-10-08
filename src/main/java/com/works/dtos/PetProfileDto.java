package com.works.dtos;

import com.works.entities.*;
import com.works.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class PetProfileDto {
    Pet petGlob= new Pet();
    Pet petCure= new Pet();
    public Integer petindex = 0;

    final StorageRepository storageRepository;
    final CustomerRepository customerRepository;
    final PetRepository petRepository;
    final CureRepository cureRepository;
    final VaccineRepository vaccineRepository;
    final LaborRepository laborRepository;
    final ProductRepository productRepository;
    final LabRepository labRepository;
    public PetProfileDto(StorageRepository storageRepository, CustomerRepository customerRepository, PetRepository petRepository, CureRepository cureRepository, VaccineRepository vaccineRepository, LaborRepository laborRepository, ProductRepository productRepository, LabRepository labRepository) {
        this.storageRepository = storageRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.cureRepository = cureRepository;
        this.vaccineRepository = vaccineRepository;
        this.laborRepository = laborRepository;
        this.productRepository = productRepository;
        this.labRepository = labRepository;
    }




    public Map<String, Object> petProfile(@PathVariable String index) {
        Integer id=Integer.parseInt(index);
        Integer petindex = id;
        Map<String, Object> hm = new LinkedHashMap();
        try {
            Pet pet=petRepository.findById(id).get();
            Pet pet1=petRepository.findById(id).get();
            petGlob = pet;
            petCure = pet1;
            List<Cure> cureList=cureRepository.findByPet_Pid(id);

            hm.put("status",true);
            hm.put("result", cureList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Lab> labList = labRepository.findByPid_PidEqualsOrderByLidDesc(id);


        return hm;
    }



    //@PostMapping("/cure")
    public Map<String, Object> cureAdd(Cure cure){
        Map<String, Object> hm = new LinkedHashMap();
        Integer temp_debt = 0;
        Storage storagePro = new Storage();
        Storage storageVac = new Storage();
        storagePro.setStaction(3);
        storageVac.setStaction(3);
        Pet pet = petCure;
        String returnlink = String.valueOf(pet.getPid());
        System.out.println(returnlink);
        try {
            cure.setPet(pet);
            System.out.println("PRO:" + cure.getCurpro());
            if( cure.getCurpro() != null)  {
                temp_debt = temp_debt + cure.getCurpro().getPrsales();
                cure.getCurpro().setPrstock(cure.getCurpro().getPrstock() - 1);
                storagePro.setStpro(cure.getCurpro());
                storagePro.setStchangeamount(1);
                storagePro.setStlastamount(cure.getCurpro().getPrstock() - 1);
                storagePro.setStdate(new Date());
                storageRepository.save(storagePro);
            }
            if (cure.getCurvac() != null) {
                temp_debt = temp_debt + cure.getCurvac().getVacsales();
                cure.getCurvac().setVacstock(cure.getCurvac().getVacstock() - 1);
                storageVac.setStvac(cure.getCurvac());
                storageVac.setStchangeamount(1);
                storageVac.setStlastamount(cure.getCurvac().getVacstock() - 1);
                storageVac.setStdate(new Date());
                storageRepository.save(storageVac);
            }
            if (cure.getCurla() != null) {
                temp_debt = temp_debt + cure.getCurla().getLamoney();
            }

            cure.setCurdebt(temp_debt);
            Cure c= cureRepository.save(cure);
            Optional<Pet> pet1 = petRepository.findById(cure.getPet().getPid());
            Optional<Customer> customerOptional = customerRepository.findById(pet1.get().getCid().getCid());
            customerOptional.get().setCdebpt(customerOptional.get().getCdebpt() + temp_debt);
            customerRepository.saveAndFlush(customerOptional.get());

            hm.put("status", true);
            hm.put("result", c);


        } catch (Exception e) {
            System.err.println("Exception:  "+ e);
        }

        return hm;
    }



}
