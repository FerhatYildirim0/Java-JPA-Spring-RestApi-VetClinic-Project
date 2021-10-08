package com.works.properties;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel(value = "PasswordChange", description = "Yeni şifre belirleme aracı")
@Data
public class PasswordChange {

    private String email;
    private String oldPass;
    private String newPass;
}
