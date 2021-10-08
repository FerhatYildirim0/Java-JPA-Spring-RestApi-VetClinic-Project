package com.works.restcontrollers;

import com.works.dtos.RegisterDto;
import com.works.entities.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/register")
public class RegisterRestController {

    final RegisterDto registerDto;

    public RegisterRestController(RegisterDto registerDto) {

        this.registerDto = registerDto;
    }
    @ApiOperation(value = "Rolleriyle Birlikte Kullanıcı Ekleme Servisi")
    @PostMapping("/add")
    public Map<String,Object> register(@RequestBody User us, @RequestParam(defaultValue = "1") String roleIDS) {

        return registerDto.register(us,roleIDS);
    }

}
