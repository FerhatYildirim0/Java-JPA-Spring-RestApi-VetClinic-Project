package com.works.dtos;
import com.works.entities.Suppliers;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserDto {

    final UserRepository userRepository;

    public UserDto(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
//////////////Şifre kriptolanacak
    public Map<String, Object> useradd(User user){
        Map<String, Object> hm = new LinkedHashMap();
        try{
            System.out.println("DTO Tarafı :  ============================================ " + user.getFirstName());
            if (user.getFirstName()!=null){
                User user1 = userRepository.saveAndFlush(user);
                hm.put("status",true);
                hm.put("result", user1);
            }
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Kullanıcı ekleme sırasında bir hata meydana geldi");
        }
        return hm;
    }

    public Map<String, Object> userdelete(String usdeleteid){
        long id = Integer.parseInt(usdeleteid);
        Map<String, Object> hm = new LinkedHashMap();
        try{
            userRepository.deleteById(id);
            hm.put("status",true);
            hm.put("result", "Kullanıcı silme işlemi başarılı");
        }
        catch (Exception ex){
            hm.put("status", false);
            hm.put("message", "Kullanıcı silme sırasında bir hata meydana geldi");

        }
        return hm;
    }
}
