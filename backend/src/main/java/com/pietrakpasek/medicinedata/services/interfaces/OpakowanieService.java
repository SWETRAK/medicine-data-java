package com.pietrakpasek.medicinedata.services.interfaces;

import com.pietrakpasek.medicinedata.model.DTO.CountResult;
import com.pietrakpasek.medicinedata.model.DTO.OpakowanieDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OpakowanieService {
    Page<OpakowanieDTO> getAllOpakowaniePage(int page, String sortBy, boolean isAscending);
    List<String> listAllSortParameters();
    List<CountResult> countKategoriaDostepnosci();
}
