package com.attech.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class ResponseUser {
    private Long id;
    private String userName;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String roleName;
    private Set<String> websites;
    private String createdDate;
    private String updatedDate;
    private String avatarName;
    private String status;
}
