package com.works.dtos;

import com.works.entities.PasswordChangeToken;
import com.works.entities.User;
import com.works.properties.PasswordChange;
import com.works.properties.PasswordToken;
import com.works.repositories.PChangeTokenRepository;

import com.works.repositories.UserRepository;
import com.works.utils.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PasswordChangeDto {

   final UserService uService;
   final UserRepository uRepo;
   final PChangeTokenRepository pChangeTokenRepository;

  public PasswordChangeDto(UserService userService, UserRepository uRepo, PChangeTokenRepository pChangeTokenRepository) {
        this.uService = userService;
        this.uRepo = uRepo;
       this.pChangeTokenRepository = pChangeTokenRepository;
   }


        public Map<String,Object> passChange(PasswordChange passwordChange){
            Map<String,Object> hm =new LinkedHashMap<>();
            UserDetails userDetails =  uService.loadUserByUsername(passwordChange.getEmail());
            System.out.println(userDetails.getUsername());

            if (userDetails.getUsername().equals(passwordChange.getEmail())){
                PasswordChangeToken passwordChangeToken=new PasswordChangeToken();
                String email=passwordChange.getEmail();
                String token = UUID.randomUUID().toString();
                Optional<User> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
                User us=oUser.get();
                passwordChangeToken.setUser(us);
                passwordChangeToken.setToken(token);
                PasswordChangeToken p=pChangeTokenRepository.save(passwordChangeToken);


                hm.put("status", true);
                hm.put("message","Şifre değiştirme işlemi başarılı");
                hm.put("result",p.getToken());
            }else {
                hm.put("status", false);
                hm.put("message", "Kullanıcı adı veya şifre hatalı");
            }
            return hm;
        }



        public Map<String,Object> passChangeWithToken(PasswordToken passwordToken){
            Map<String,Object> hm =new LinkedHashMap<>();
            UserDetails userDetails =  uService.loadUserByUsername(passwordToken.getEmail());

            PasswordChangeToken passwordChangeToken= pChangeTokenRepository.findByTokenEquals(passwordToken.getToken());
            if (passwordChangeToken != null) {

                hm.put("result", passwordChangeToken);
                hm.put("message", "token ");
                if (userDetails.getUsername().equals(passwordToken.getEmail())){
                    System.out.println(userDetails);
                    String email=passwordToken.getEmail();
                    Optional<User> oUser = uRepo.findByEmailEqualsAllIgnoreCase(email);
                    User us=oUser.get();
                    us.setPassword( uService.encoder().encode(passwordToken.getNewPass()));
                    uRepo.saveAndFlush(us);
                    hm.put("message" ,"Şifre değiştirme başarılı. ");
                }

            }else {

                hm.put("result", "Email hatalı ");
                hm.put("message", "şifre yada email hatalı ");
            }
            return hm;
        }

}




