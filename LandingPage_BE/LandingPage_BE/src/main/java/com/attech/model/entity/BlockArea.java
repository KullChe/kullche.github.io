package com.attech.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="areaCode")
    private String areaCode;
    @Column(name="image")
    private String image;
    @Column(name = "textTitle")
    private String textTitle;
    @Column(name = "textContent")
    private String textContent;
    @Column(name="status")
    private Boolean status;
    @Column(name = "created_at")
    private Date createdDate;
    @Column(name = "updated_at")
    private Date updatedDate;

}
