package com.pietrakpasek.medicinedata.repositories;

import com.pietrakpasek.medicinedata.model.entities.produkt_leczniczy.SubstancjaCzynna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubstancjaCzynnaRepository extends JpaRepository<SubstancjaCzynna, Integer> {
}
