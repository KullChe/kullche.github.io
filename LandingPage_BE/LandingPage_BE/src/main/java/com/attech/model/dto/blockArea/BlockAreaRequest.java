package com.attech.model.dto.blockArea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BlockAreaRequest {
    @NotEmpty(message = "*Please provide area code")
    private String areaCode;

    private MultipartFile image;

    private String textTitle;

    private String textContent;
}
