package com.attech.model.dto.blockArea;

import javax.persistence.Column;
import java.util.Set;

public class ResponseBlockArea {
    private Long id;
    private String areaCode;
    private Set<String> image;
    private String textTitle;
    private String textContent;
    private String status;
    private String createdDate;
    private String updatedDate;
}
