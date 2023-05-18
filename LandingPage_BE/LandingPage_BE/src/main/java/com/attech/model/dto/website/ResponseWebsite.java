package com.attech.model.dto.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWebsite {
    private Long id;
    private String name;
    private String dateCreate;
    private String lastEdited;
    private String aliasWebsite;
    private String nameServer;
    private String domain;
    private String tokenWebsite;
}
