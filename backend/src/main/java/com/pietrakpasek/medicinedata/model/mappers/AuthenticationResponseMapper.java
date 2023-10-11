package com.pietrakpasek.medicinedata.model.mappers;

import com.pietrakpasek.medicinedata.model.DTO.AuthenticationResponseDTO;
import com.pietrakpasek.medicinedata.security.AuthenticationResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationResponseMapper {
    AuthenticationResponseDTO authenticationResponseToAuthenticationResponseDTO(AuthenticationResponse authenticationResponse);
}
