package com.works.model;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class BaseAgenda {
    @Id
    private String id;

    private String agtitle;
    private String agtext;
    private String agdate;
    private String agtime;
}
