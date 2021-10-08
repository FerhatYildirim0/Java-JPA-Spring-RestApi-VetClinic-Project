package com.works.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
@ApiModel(value = "Lab", description = "Evcil hayvanların üstünden tedavi kaydetmek için kullanılır.")
@Entity
@Getter
@Setter
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid", nullable = false)
    private Integer lid;

    @ApiModelProperty(value = "Labaratuvar Başlığı")
    private String ltitle;

    @ApiModelProperty(value = "Labaratuvar Yorumu")
    private String lcomment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pid")
    private Pet pid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ldate;

    @ApiModelProperty(value = "Dosya İsmi")
    private String fileName;

}
