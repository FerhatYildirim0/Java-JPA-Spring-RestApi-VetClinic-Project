package com.works.restcontrollers;

import com.works.dtos.UserDto;
import com.works.entities.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {

    final UserDto userDto;

    public UserRestController(UserDto userDto) {
        this.userDto = userDto;
    }

    @ApiOperation(value = "Kullanıcı Ekleme Servisi")
    @PostMapping("/add")
    public Map<String, Object> useradd(@RequestBody User user){
        return userDto.useradd(user);
    }

    @ApiOperation(value = "Kullanıcı Silme Servisi")
    @DeleteMapping("/delete/{usdeleteid}")
    public Map<String, Object> sudelete(@PathVariable String usdeleteid){


        return userDto.userdelete(usdeleteid);
    }


}
