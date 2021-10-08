package com.works.dtos;


import com.works.entities.MoneyCase;
import com.works.entities.Sale;
import com.works.repositories.CaseRepository;
import com.works.repositories.SaleRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleDto {

    final SaleRepository saleRepository;
    final CaseRepository caseRepository;


    public SaleDto(SaleRepository saleRepository, CaseRepository caseRepository) {
        this.saleRepository = saleRepository;
        this.caseRepository = caseRepository;
    }


    public Map<String,Object> sainsert(Sale sale){
        Map<String,Object> hm =new LinkedHashMap<>();

        try{ sale.setSaleDate(new Date());
            float tax=0.3f;
            sale.setGrossPrice(sale.getPamount()*sale.getPprice());
            sale.setNetPrice(sale.getGrossPrice()-sale.getGrossPrice()*sale.getPdiscount()/100);
            if(sale.getTax_type()==0){
                tax = 0;
            }
            else if(sale.getTax_type()==1){
                tax = 0.01f;
            }
            else if(sale.getTax_type()==2){
                tax = 0.08f;
            }
            else if(sale.getTax_type()==3){
                tax = 0.18f;
            }

            sale.setTotalPrice((int) (sale.getNetPrice()*(1+tax)));

            Sale s = saleRepository.save(sale);

            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            String email = aut.getName(); // username

            // KASA
            MoneyCase moneyCase = new MoneyCase();
            moneyCase.setCmoney(sale.getTotalPrice());
            moneyCase.setCbuysell(1);
            moneyCase.setCdatenow(new Date());
            moneyCase.setCpeople(sale.getCname());
            moneyCase.setCcomment("Satış");
            moneyCase.setCname(email);
            caseRepository.save(moneyCase);

            hm.put("status", true);
            hm.put("result", sale);
            hm.put("message", "Satış işlemi kaydedildi");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Satış işlemi kaydedilirken hata " + ex);
        }

        return hm;
    }




    public Map<String, Object> saDelete(String index){
        Map<String, Object> hm =new LinkedHashMap<>();

        try{
            int sid = Integer.parseInt(index);
            saleRepository.deleteById(sid);
            hm.put("status", true);
            hm.put("message", "Satış silme işlemi başarılı");


        } catch (Exception ex){
            hm.put("status", false);
            hm.put("message" ,"Satış silme işlemi sırasında hata");
        }


        return hm;
    }

 //Belirtilen fatura nodaki statusleri 1 yapar
    public Map<String, Object> statusChange(String sindex){
        Map<String, Object> hm =new LinkedHashMap<>();
        int faturaNo = Integer.parseInt(sindex);
        try{

            List<Sale> saleList = saleRepository.getByFaturaNoEquals(faturaNo);

            for (Sale sale: saleList){

                sale.setStatus(true);
            }

            saleRepository.saveAllAndFlush(saleList);
            hm.put("status", true);
            hm.put("message","Status değiştirme başarılı.");


        }

        catch (Exception ex){
            hm.put("status", false);
            hm.put("message","Status değiştirme başarısız.");
        }


        return hm;
    }





}
