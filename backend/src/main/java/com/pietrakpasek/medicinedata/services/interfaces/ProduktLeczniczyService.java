package com.pietrakpasek.medicinedata.services.interfaces;

import com.pietrakpasek.medicinedata.model.DTO.CountResult;
import com.pietrakpasek.medicinedata.model.DTO.GeneratedFileParams;
import com.pietrakpasek.medicinedata.model.DTO.ProduktLeczniczyDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduktLeczniczyService {
    Page<ProduktLeczniczyDTO> getAllProduktLeczniczyPage(int page, String sortBy, boolean isAscending);
    Page<ProduktLeczniczyDTO> getProduktLeczniczyByNamePage(String name, int page, String sortBy, boolean isAscending);
    List<CountResult> getSubstancjaCzynnaCountTop10();
    List<CountResult> getPostacCountTop10();

    void deleteProduktLeczniczyById(Integer id);
    byte[] getProduktLeczniczyXmlFile(GeneratedFileParams params);
    byte[] getProduktLeczniczyJsonFile(GeneratedFileParams params);
    List<String> listAllSortParameters();
}
