package com.works.dtos;

import com.works.entities.Buying;
import com.works.entities.MoneyCase;
import com.works.entities.Storage;
import com.works.entities.Suppliers;
import com.works.repositories.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class BuyingDto {

    final BuyingRepository buyingRepository;
    final SuppliersRepository suppliersRepository;
    final StorageRepository storageRepository;
    final VaccineRepository vaccineRepository;
    final ProductRepository productRepository;
    final CaseRepository caseRepository;

    public BuyingDto(BuyingRepository buyingRepository, SuppliersRepository suppliersRepository, StorageRepository storageRepository, VaccineRepository vaccineRepository, ProductRepository productRepository, CaseRepository caseRepository) {
        this.buyingRepository = buyingRepository;
        this.suppliersRepository = suppliersRepository;
        this.storageRepository = storageRepository;
        this.vaccineRepository = vaccineRepository;
        this.productRepository = productRepository;
        this.caseRepository = caseRepository;
    }


    public Map<String,Object> buinsert(Buying buying){
        Map<String,Object> hm =new LinkedHashMap<>();

        try {

            float tax=0.3f;
            buying.setBuyingDate(new Date());
            buying.setBgrossPrice(buying.getProprice()*buying.getProamount());
            buying.setBnetPrice(buying.getBgrossPrice()-buying.getBgrossPrice()*buying.getPdiscount()/100);
            buying.setStatus(0);

            if(buying.getTaxtype()==0){
                tax = 0;
            }
            else if(buying.getTaxtype()==1){
                tax = 0.01f;
            }
            else if(buying.getTaxtype()==2){
                tax = 0.08f;
            }
            else if(buying.getTaxtype()==3){
                tax = 0.18f;
            }

            buying.setBtotalPrice((int) (buying.getBnetPrice()*(1+tax)));
            Buying b = buyingRepository.saveAndFlush(buying);

            hm.put("status", true);
            hm.put("result", b);
            hm.put("message", "Satın alım işlemi başarılı ");

        } catch (Exception ex) {
            hm.put("status", false);
            hm.put("message", "Satın alım gerçekleşirken hata"+ ex);
        }
        return hm;
    }

   //Belirtilen id'ye göre siler
    public Map<String,Object> buListDelete(String bidx){
        Map<String,Object> hm =new LinkedHashMap<>();
        int bid = Integer.parseInt(bidx);
        try{

            Buying dObject = buyingRepository.findByBidEquals(bid);

            List<Buying> dlist = buyingRepository.getByBfaturaNoEquals(dObject.getBfaturaNo());

            //  buyingRepository.deleteAll(dlist);

            for(Buying ditem : dlist){
                buyingRepository.delete(ditem);
            }
            hm.put("status", true);
            hm.put("message", "List Buying silme başarılı ");

        } catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "List Buying silme sırasında hata");
        }

        return hm;
    }


   //Belirtilen faturo noların statuslerini 1 yapıp toplar daha sonra yeni obje oluşturup statusunü 2 yaptıktan sonra toplanan sayıları ona atar
    public Map<String,Object> buStatusChange(String scfatno){
        Map<String,Object> hm =new LinkedHashMap<>();
        int bfaturaNo = Integer.parseInt(scfatno);
        try{
            Buying buyingObject = new Buying();

            int objGrossPrice = buyingObject.getBgrossPrice();
            int objNetPrice   = buyingObject.getBnetPrice();
            int objTotalPrice = buyingObject.getBtotalPrice();
            int objFaturaNo = buyingObject.getBfaturaNo();
            int objDiscount = buyingObject.getPdiscount();
            int objPurType = buyingObject.getPurtype();
            String objTname = buyingObject.getTname();
            int objTaxType = buyingObject.getTaxtype();
            String objPname = buyingObject.getPname();

            List<Buying> buyingList = buyingRepository.getByBfaturaNoEquals(bfaturaNo);

            for (Buying buying: buyingList){
                buying.setStatus(1);
                objFaturaNo = buying.getBfaturaNo();
                objGrossPrice += buying.getBgrossPrice();
                objNetPrice += buying.getBnetPrice();
                objTotalPrice += buying.getBtotalPrice();
                objDiscount += buying.getPdiscount();
                objTname = buying.getTname();
                objPname = buying.getPname();
                objPurType = buying.getPurtype();
                objTaxType = buying.getTaxtype();


                // product vaccine güncelleme
                Integer pname = Integer.parseInt(buying.getPname());

                if(vaccineRepository.findById(pname).isPresent()) {
                    int temp = vaccineRepository.findById(pname).get().getVacstock();
                    vaccineRepository.findById(pname).get().setVacstock(temp + buying.getProamount());

                    Storage storage = new Storage();
                    storage.setStaction(1);
                    storage.setStdate(new Date());
                    storage.setStchangeamount(buying.getProamount());
                    storage.setStlastamount(temp + buying.getProamount());
                    storage.setStvac(vaccineRepository.findById(pname).get());
                    storageRepository.save(storage);
                }
                else if (productRepository.findById(pname).isPresent()) {
                    int temp = productRepository.findById(pname).get().getPrstock();
                    productRepository.findById(pname).get().setPrstock(temp + buying.getProamount());

                    Storage storage = new Storage();
                    storage.setStaction(1);
                    storage.setStdate(new Date());
                    storage.setStchangeamount(buying.getProamount());
                    storage.setStlastamount(temp + buying.getProamount());
                    storage.setStpro(productRepository.findById(pname).get());
                    storageRepository.save(storage);
                }
                else {
                    System.out.println("");
                }

                Authentication aut = SecurityContextHolder.getContext().getAuthentication();
                String email = aut.getName(); // username

                // KASA
                MoneyCase moneyCase = new MoneyCase();
                moneyCase.setCmoney(buying.getBtotalPrice());
                moneyCase.setCbuysell(2);
                moneyCase.setCdatenow(new Date());
                moneyCase.setCpeople(suppliersRepository.findById(Integer.parseInt(buying.getTname())).get().getSuname());
                moneyCase.setCcomment("Fatura");
                moneyCase.setCname(email);
                caseRepository.save(moneyCase);
            }

            buyingObject.setBfaturaNo(objFaturaNo);
            buyingObject.setBgrossPrice(objGrossPrice);
            buyingObject.setBnetPrice(objNetPrice);
            buyingObject.setBtotalPrice(objTotalPrice);
            buyingObject.setBuyingDate(new Date());
            buyingObject.setPdiscount(objDiscount/(buyingList.size()));
            buyingObject.setPurtype(objPurType);
            buyingObject.setTaxtype(objTaxType);
            buyingObject.setTname(objTname);
            buyingObject.setPname(objPname);

            buyingObject.setStatus(2);
            buyingRepository.saveAndFlush(buyingObject);
            buyingRepository.saveAllAndFlush(buyingList);


            // Tedarikçiye borç ekleme
            int tname = Integer.parseInt(buyingObject.getTname());
            Suppliers suppliers = suppliersRepository.findById(tname).get();
            suppliers.setSudebt(suppliers.getSudebt()+buyingObject.getBtotalPrice());
            suppliersRepository.saveAndFlush(suppliers);

            // List<Buying> buyings = buyingRepository.findByPnameEqualsIgnoreCaseAndStatusEqualsOrderByBidDesc(buyingObject.getTname(), 1);

            hm.put("status", true);
            hm.put("message", "Status değişti ");

        }

        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Status değişimi sırasında hata :  "+ex.getLocalizedMessage());
        }
       return hm;
    }

    public Map<String,Object> BuyingComplatedList(){
        Map<String,Object> hm =new LinkedHashMap<>();

        try {
            List<Buying> b= buyingRepository.findByStatusEquals(2);
            hm.put("status", true);
            hm.put("result", b);
            hm.put("message", "Status değişenler listelendi");

        } catch (Exception ex) {
            hm.put("status", false);
            hm.put("message", "Status değişiminde hata  : "+ ex);
        }


        return hm;
    }

    //Sepettekini siler
    public Map<String,Object> buDelete(String pdindex){
        Map<String,Object> hm =new LinkedHashMap<>();
        int bid = Integer.parseInt(pdindex);
        try{
            buyingRepository.deleteById(bid);
            hm.put("status",true);
            hm.put("message", "Satın alım silme işlemi başarılı");
        }
        catch (Exception ex ){
            hm.put("status", false);
            hm.put("message","Satın alım silme işlemi sırasında hata"+ ex);
        }


        return hm;
    }



    //Sepetteki ürünleri listeler. Statusu 1 olanları listeler.
    public Map<String,Object> buDetail(String detailNo){
        Map<String,Object> hm =new LinkedHashMap<>();

        int bfaturaNo = Integer.parseInt(detailNo);
        List<Buying> buyings = buyingRepository.findByBfaturaNoEqualsAndStatusEquals(bfaturaNo, 1);
        try{

            buyingRepository.saveAllAndFlush(buyings);
            hm.put("status", true);
            hm.put("result",buyings);
            hm.put("message", "Detaylar listelendi ");

        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Detay listeleme sırasında hata : "+ex);
        }

        return hm;
    }







}
