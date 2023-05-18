package com.attech.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class
ResponseObject {
    private String resCode;
    private String resDesc;
    private Object resData;
}
