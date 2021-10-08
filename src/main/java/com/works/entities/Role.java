package com.works.entities;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;
@ApiModel(value = "Role", description = "Yetki düzeyi için rol ataması yapar.")

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
