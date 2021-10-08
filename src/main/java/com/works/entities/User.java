package com.works.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@ApiModel(value = "User", description = "Sistemi kullanacak kullanıcıları eklemek için kullanılır")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty(value = "Kullanıcı İsmi")
    private String firstName;

    @ApiModelProperty(value = "Kullanıcı Soyismi")
    private String lastName;

   @ApiModelProperty(value = "Kullanıcı Email'i")
    private String email;

    @ApiModelProperty(value = "Kullanıcı Şifresi")
    private String password;

    @ApiModelProperty(value = "Kullanıcı Erişim Durum Değişkeni")
    private boolean enabled;

    @ApiModelProperty(value = "")
    private boolean tokenExpired;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
}
