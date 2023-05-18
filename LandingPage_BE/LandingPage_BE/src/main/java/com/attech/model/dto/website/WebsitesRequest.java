package com.attech.model.dto.website;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebsitesRequest {
    @NotEmpty(message = "*Please provide a website name")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String name;
    @NotEmpty(message = "*Please provide a aliasWebsite")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    private String aliasWebsite;
    @NotEmpty(message = "*Please provide a nameServer")
    private String nameServer;
    @NotEmpty(message = "*Please provide a domain")
    private String domain;
    @NotEmpty(message = "*Please provide a tokenWebsite")
    private String tokenWebsite;
}
