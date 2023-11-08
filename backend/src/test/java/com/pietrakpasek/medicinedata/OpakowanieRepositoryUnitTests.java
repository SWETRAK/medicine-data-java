package com.pietrakpasek.medicinedata;

import com.pietrakpasek.medicinedata.model.DTO.CountResult;
import com.pietrakpasek.medicinedata.repositories.OpakowanieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class OpakowanieRepositoryUnitTests {

    @Autowired
    private OpakowanieRepository opakowanieRepository;

    @Test
    void test_countKategoriaDostepnosci() {
        List<CountResult> results = opakowanieRepository.countKategoriaDostepnosci();

        assertThat(results).isNotNull();
        assertThat(results).isNotEmpty();

        results.sort((o1, o2) -> Math.toIntExact(o2.value() - o1.value()));

        CountResult firstRow = (CountResult) results.toArray()[0];
        assertThat(firstRow.name()).isEqualTo("Rp");
    }
}
