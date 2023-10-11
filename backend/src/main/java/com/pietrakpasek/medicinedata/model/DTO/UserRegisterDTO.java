package com.pietrakpasek.medicinedata.model.DTO;

import com.pietrakpasek.medicinedata.validation.Password;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    @Password
    private String password;

    @Password
    private String repeatPassword;

    @Email
    private String email;

    @NonNull
    private String role;

    public UserRegisterDTO() {
    }
}
