package com.works.properties;

import io.swagger.annotations.ApiModel;
import lombok.Data;
@ApiModel(value = "PasswordToken", description = "Yeni şifre belirlemek için sistem tarafından otomatik oluşturuluan token'ı veri tabanına götürür.")
@Data
public class PasswordToken {

    private String email;

    private String newPass;
    private String token;

}
