package com.pietrakpasek.medicinedata.model.DTO;

import com.pietrakpasek.medicinedata.model.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDTO {
    private String email;
    private Role role;

    public AuthenticationResponseDTO(String email, Role role) {
        this.email = email;
        this.role = role;
    }
}
