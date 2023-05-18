package com.attech.model.dto.authen;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
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
    private Long id;
}
