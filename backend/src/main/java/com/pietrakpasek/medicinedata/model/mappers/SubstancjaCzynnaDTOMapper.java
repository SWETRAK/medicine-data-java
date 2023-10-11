package com.pietrakpasek.medicinedata.model.mappers;

import com.pietrakpasek.medicinedata.model.DTO.SubstancjaCzynnaDTO;
import com.pietrakpasek.medicinedata.model.entities.produkt_leczniczy.SubstancjaCzynna;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubstancjaCzynnaDTOMapper {
    SubstancjaCzynnaDTO substancjaCzynnaToSubstancjaCzynnaDTO(SubstancjaCzynna substancjaCzynna);
}
