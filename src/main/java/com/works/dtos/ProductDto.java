package com.works.dtos;
import com.works.entities.Product;

import com.works.entities.Vaccine;
import com.works.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductDto {

    final ProductRepository productRepository;

    public ProductDto(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Map<String, Object> prlistdto(){
        Map<String, Object> hm = new LinkedHashMap();

        List<Product> ls = productRepository.findAll();
        if(ls.size() > 0){
            hm.put("status", true);
            hm.put("result", ls);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Ürün listelenirken bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> prSingle(String proid){
        Map<String, Object> hm = new LinkedHashMap();
        int prid=Integer.parseInt(proid);

        Optional<Product> product = productRepository.findById(prid);
        if(product.isPresent()){
            hm.put("status", true);
            hm.put("result", product);
        }

        else{
            hm.put("status", false);
            hm.put("message", "Ürün bilgileri yüklenirken bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> praddUpdate(Product product){
        Map<String, Object> hm = new LinkedHashMap();
        try{
        //    System.out.println("DTO Tarafı :  ============================================ " + suppliers.getSuname());
            if (product.getPrname()!=null){
                Product product1 = productRepository.saveAndFlush(product);
                hm.put("status",true);
                hm.put("result", product1);
            }
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Ürün ekleme sırasında bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> prdelete(String prdeleteid){
        int prid = Integer.parseInt(prdeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            productRepository.deleteById(prid);
            hm.put("status",true);
            hm.put("result", "Ürün silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Ürün silme sırasında bir hata meydana geldi");

        }
        return hm;
    }

}
