package com.works.restcontrollers;

import com.works.dtos.PasswordChangeDto;
import com.works.properties.PasswordChange;
import com.works.properties.PasswordToken;
import com.works.repositories.UserRepository;
import com.works.utils.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/password")
public class PasswordChangeRestController {
    final PasswordChangeDto passChangeDto;
    final UserService userService;
    final UserRepository userRepository;

    public PasswordChangeRestController(PasswordChangeDto passChangeDto, UserService userService, UserRepository userRepository) {
        this.passChangeDto = passChangeDto;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "Şifre Değiştirme Servisi. 2. Aşama Request'te gelen token burada parametre olarak gönderilir")
    @PostMapping("/changer")
    public Map<String, Object> passChangeRequest(@RequestBody PasswordToken passToken) {

        return passChangeDto.passChangeWithToken(passToken);
    }
    @ApiOperation(value = "Şifre değiştirme isteği yollama Servisi")
    @PostMapping("/request")
    public Map<String, Object> passChanger(@RequestBody PasswordChange passChange) {

        return passChangeDto.passChange(passChange);

    }
}
