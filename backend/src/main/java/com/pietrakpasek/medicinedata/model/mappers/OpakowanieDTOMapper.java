package com.pietrakpasek.medicinedata.model.mappers;

import com.pietrakpasek.medicinedata.model.DTO.OpakowanieDTO;
import com.pietrakpasek.medicinedata.model.entities.produkt_leczniczy.Opakowanie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OpakowanieDTOMapper {
    OpakowanieDTO opakowanieToOpakowanieDTO(Opakowanie opakowanie);
}
