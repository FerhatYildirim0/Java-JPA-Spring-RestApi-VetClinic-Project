package com.works.restcontrollers;

import com.works.dtos.LabDto;
import com.works.entities.Lab;
import com.works.properties.LabProperty;
import com.works.repositories.LabRepository;
import com.works.repositories.PetRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("/lab")
public class LabRestController {

    private final String UPLOAD_DIR =  "src/main/resources/static/uploads/";
    long maxFileUploadSize = 2048;
    final LabDto labDto;
    final PetRepository petRepository;
    final LabRepository labRepository;
    public LabRestController(LabDto labDto, PetRepository petRepository, LabRepository labRepository) {
        this.labDto = labDto;
        this.petRepository = petRepository;
        this.labRepository = labRepository;
    }

    @GetMapping("/list")
    public Map<String, Object> lablist(){

    return labDto.labList();
    }



    @PostMapping("/add")
    public Map<String, Object> labadd(@RequestParam("fileName") MultipartFile file, LabProperty labProperty) {
        int sendSuccessCount = 0;
        String errorMessage = "";
        Map<String, Object> hm = new LinkedHashMap<>();

        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    sendSuccessCount += 1;

                    // add database
                    System.out.println(labProperty);
                    //image.setPid(pid);
                    Lab lab=new Lab();
                    lab.setLcomment(labProperty.getLcomment());
                    lab.setLtitle(labProperty.getLtitle());
                    lab.setFileName(fileName);
                    lab.setPid( petRepository.findById(labProperty.getPid()).get());
                    lab.setLdate(new Date());
                    labRepository.save(lab);

                    //image.setFileName(fileName);
                    //piRepo.save(image);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put("status", true);
            hm.put("message", "Resim başarıyla yüklendi");
        }else {
            hm.put("status", false);
            hm.put("message", errorMessage);
        }

        return hm;
    }
    @DeleteMapping("delete/{deleteid}")
    public Map<String , Object> deleteLabresult(@PathVariable String deleteid ) {
        Map<String, Object> hm =new LinkedHashMap<>();
        try {
            int lid = Integer.parseInt( deleteid );
            Optional<Lab> opi = labRepository.findById(lid);
            if ( opi.isPresent() ) {
                labRepository.deleteById(lid);
                // file delete
                String deleteFilePath = opi.get().getFileName();
                File file = new File(UPLOAD_DIR+deleteFilePath);
                file.delete();
                hm.put("status", true);
                hm.put("message", "Labaratuvar sonucu başarıyla silindi");
            }
        }catch (Exception ex) {
            hm.put("status", true);
            hm.put("message", "Labaratuvar sonucu silme işlemi sırasında bir hata oluştu!"+ex);
        }
        return hm;
    }
}
